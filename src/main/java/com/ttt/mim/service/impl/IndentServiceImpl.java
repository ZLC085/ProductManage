package com.ttt.mim.service.impl;

import com.alibaba.druid.sql.visitor.functions.Insert;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ttt.mim.common.exception.BusinessException;
import com.ttt.mim.common.utils.PoiExcelTools;
import com.ttt.mim.common.utils.PoiWordTools;
import com.ttt.mim.common.utils.RandomString;
import com.ttt.mim.dao.*;
import com.ttt.mim.domain.*;
import com.ttt.mim.service.IndentService;
import com.ttt.mim.service.PurchaseService;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service("indentService")
public class IndentServiceImpl implements IndentService {
    private final IndentDao indentDao;
    private final PurchaseDao purchaseDao;
    private final QuoteDao quoteDao;
    private final PurchaseService purchaseService;
    private final IndentOutDataDao indentOutDataDao;
    private final CustomerDao customerDao;
    private final IndentDetailDao indentDetailDao;
    private final Logger logger=Logger.getLogger(this.getClass());

    @Autowired
    public IndentServiceImpl(IndentDao indentDao, PurchaseDao purchaseDao, QuoteDao quoteDao, PurchaseService purchaseService, IndentOutDataDao indentOutDataDao, CustomerDao customerDao, IndentDetailDao indentDetailDao) {
        this.indentDao = indentDao;
        this.purchaseDao = purchaseDao;
        this.quoteDao = quoteDao;
        this.purchaseService = purchaseService;
        this.indentOutDataDao = indentOutDataDao;
        this.customerDao = customerDao;
        this.indentDetailDao = indentDetailDao;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add(JSONObject data) {
        int affectedRows=0;
        Map<String, Object> map = convertJsonData(data);
        Indent indent= (Indent) map.get("indent");
        indent.setNum(getAvailableIndentNum());
        List<Quote> quoteList= (List<Quote>) map.get("quotes");
        Map<Purchase, List<PurchaseDetail>> purchaseListMap = purchaseService.convertJsonData(data);
        for (Purchase purchase:purchaseListMap.keySet()){
            purchase.setName("订单("+indent.getNum()+")附属采购单");
            purchase.setIndentNum(indent.getNum());
        }

        int indentRows=add(indent, quoteList);
        affectedRows += indentRows;

        int purchaseRows=purchaseService.add(purchaseListMap);
        affectedRows += purchaseRows;
        return affectedRows;
    }

    @Transactional(rollbackFor = Exception.class)
    public int add(Indent indent,List<Quote> quoteList) {
        int affectedRows=0;
        Date now=new Date();
        indent.setCreateTime(now);
        indent.setModifyTime(now);

        for (Quote quote :quoteList){
            quote.setIndentNum(indent.getNum());
            quote.setCreateTime(now);
        }
        /*数据检验开始*/
        if(indent.addCheckData()){
            Boolean tmp=true;
            for (Quote quote:quoteList){
                if(!quote.addCheckData()){
                    logger.error("报价数据校验不通过："+quote);
                    throw new BusinessException("报价数据检验不通过!");
                }
            }
        }else {
            logger.error("订单数据校验不通过："+indent);
            throw new BusinessException("订单数据检验不通过!");
        }
        /*数据校验结束*/

        affectedRows+=indentDao.add(indent);
        affectedRows+=quoteDao.batchAdd(quoteList);

        return affectedRows;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(Indent indent) {
        int affectedRows=0;
        if(isRevisable(indent.getNum())){
            indent.setModifyTime(new Date());
            if(!indent.updateCheckData()){
                logger.error("订单数据检验不通过："+indent);
                throw new BusinessException("订单数据检验不通过！");
            }
            affectedRows+=indentDao.update(indent);
        }
        return affectedRows;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(String indentNum) {
        int affectedRows=0;
        if(!isRevisable(indentNum)){
            return affectedRows;
        }
        affectedRows += quoteDao.deleteByIndentNum(indentNum);
        affectedRows += purchaseService.deleteByIndentNum(indentNum);
        affectedRows += indentDao.delete(indentNum);
        return affectedRows;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int batchDelete(String[] indentNums) {
        int affectedRows=0;
        for (String num : indentNums){
            int tmp=delete(num);
            if(tmp==0){
                throw new BusinessException("删除订单失败");
            }
            affectedRows+=tmp;
        }
        return affectedRows;
    }

    @Override
    public Map<String, byte[]> getAllDocx(HttpServletRequest request, String[] indentNums) {
        Map<String,byte[]> map=new HashMap<>();
        for (String indentNum:indentNums){
            if(!isRevisable(indentNum)){
                return null;
            }
        }
        List<byte[]> docxBytes=new ArrayList<>();
        for (String indentNum:indentNums){
            String desPath=generateDocx(request, indentNum);
            System.out.println(desPath);

            if (desPath!=null){
                try {
                    byte[] bytes=getBytesFromFile(new File(desPath));
                    docxBytes.add(bytes);
                    map.put(indentNum+".docx", bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return map;
    }
    public Map<String, byte[]> getAllExcel(HttpServletRequest request, String[] indentNums) throws IOException {
        Map<String,byte[]> map=new HashMap<>();
        for (String indentNum:indentNums){
            if(!isRevisable(indentNum)){
                return null;
            }
        }
        List<byte[]> excelBytes=new ArrayList<>();
        for (String indentNum:indentNums){
            String desPath=generateExcel(request, indentNum);
            if (desPath!=null){
                try {
                    byte[] bytes=getBytesFromFile(new File(desPath));
                    excelBytes.add(bytes);
                    map.put(indentNum+".xlsx", bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return map;
    }

    @Override
    public Indent get(String indentNum) {
        return indentDao.get(indentNum);
    }

    @Override
    public List<IndentOutData> query(Map<String, Object> map) {
        return indentOutDataDao.query(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return indentOutDataDao.count(map);
    }

    @Override
    public JSONObject getDetails(String indentNum) {
        JSONObject jsonObject=new JSONObject();

        IndentOutData indentOutData=indentOutDataDao.get(indentNum);
        if(indentOutData==null){
            return  null;
        }
        jsonObject.put("indent", indentOutData);
        Customer customer=customerDao.findById(indentOutData.getCustomerId());
        jsonObject.put("customer", customer);
        List<IndentDetailData> indentDetailDataList=indentDetailDao.getByIndentNum(indentNum);
        jsonObject.put("materials", indentDetailDataList);
        System.out.println(jsonObject);
        return jsonObject;
    }

    @Override
    public String getAvailableIndentNum() {
        LocalDate now=LocalDate.now();
        String month=String.format("%02d", now.getMonthValue());
        String day=String.format("%02d", now.getDayOfMonth());
        String todayNumStr=now.getYear()+month+day;
        String todayMaxIndentNum=indentDao.getTodayMaxIndentNum(todayNumStr);
        int availableCount=1;
        if(todayMaxIndentNum==null){
            return todayNumStr+String.format("%07d", availableCount);
        }
        availableCount=Integer.valueOf(todayMaxIndentNum.substring(8, 15));
        if(availableCount<9999999){
            availableCount++;
        }else {
            return null;
        }
        String availableIndentNum=todayMaxIndentNum.substring(0,8)+String.format("%07d", availableCount);
        return availableIndentNum;
    }

    @Override
    public Boolean isRevisable(String indentNum) {
        List<Byte> list=purchaseDao.getResultsByIndentNum(indentNum);
        if(list==null || list.size()==0){
            return false;
        }
        for (Byte b :list){
            if(b==null || b ==(byte) 1){
                return false;
            }
        }
        return true;
    }

    public Map<String,Object> convertJsonData(JSONObject jsonObject){
        Map<String,Object> map=new HashMap<>();
        Indent indent=JSONObject.parseObject(jsonObject.get("indent").toString(), Indent.class);

        Double quoteMoney=0.0;
        Double costMoney=0.0;
        List<Quote> quoteList=new ArrayList<>();
        JSONArray array=jsonObject.getJSONArray("materials");
        for (Object o:array){
            JSONObject json=JSONObject.parseObject(o.toString());
            Integer mId=json.getInteger("mId");
            Integer mcId=json.getInteger("mcId");
            Double quote=json.getDouble("quote");
            Integer number=json.getInteger("number");
            Double sum=quote*number;

            Double price=json.getDouble("price");
            quoteMoney += sum;
            costMoney+=price*number;

            Quote quote2=new Quote(null, indent.getNum(), mId, mcId, quote, number, sum, null);
            quoteList.add(quote2);
        }

        indent.setCostMoney(costMoney);
        indent.setQuoteMoney(quoteMoney);
        map.put("indent",indent);
        map.put("quotes", quoteList);

        return map;
    }
    //生成单个docx
    private String generateDocx(HttpServletRequest request,String indentNum){
        String srcPath=request.getServletContext().getRealPath("/WEB-INF/office/template.docx");
        String desPath=request.getServletContext().getRealPath("/WEB-INF/office/"+ indentNum +".docx");
        IndentOutData indentOutData=indentOutDataDao.get(indentNum);
        if(indentOutData==null){
            return null;
        }
        Customer customer=customerDao.findById(indentOutData.getCustomerId());
        List<IndentDetailData> indentDetailDataList=indentDetailDao.getByIndentNum(indentNum);

        Map<String,String> map=new HashMap<>();
        map.put("indentNum",indentOutData.getIndentNum());
        map.put("indentName",indentOutData.getIndentName());
        map.put("creator",indentOutData.getCreatorName());
        map.put("customerName",indentOutData.getCustomerName());
        map.put("costSum",indentOutData.getCostMoney()+"");
        map.put("quoteSum",indentOutData.getQuoteMoney()+"");
        Instant instant=Instant.ofEpochMilli(indentOutData.getCreateTime().getTime());
        LocalDateTime cre=LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        map.put("createTime",cre.toLocalDate()+" "+cre.toLocalTime());
        instant=Instant.ofEpochMilli(indentOutData.getModifyTime().getTime());
        cre=LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        map.put("modifyTime",cre.toLocalDate()+" "+cre.toLocalTime());

        map.put("phone", customer.getPhone());
        map.put("socialCode", customer.getSocialCode());
        map.put("legalPerson", customer.getLegalPerson());
        map.put("status", customer.getStatus());
        map.put("industry", customer.getIndustry());
        map.put("credit", customer.getCredit());

        List<String[]> tableList=new ArrayList<>();
        for (IndentDetailData detail :indentDetailDataList){
            String[] strArray=new String[]{detail.getMName(),detail.getFactoryName(),detail.getSpec(),detail.getNumber()+"",detail.getQuote()+"",detail.getSum()+""};
            tableList.add(strArray);
        }
        File file=new File(desPath);
        if(file.exists()){
            file.delete();
        }
        Boolean flag = PoiWordTools.changeWord(srcPath, desPath, map, tableList);
        if (flag==false){
            return null;
        }else{
            return desPath;
        }

    }
    // 返回一个byte数组
    public byte[] getBytesFromFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);// 获取文件大小
        long lengths = file.length();
        System.out.println("lengths = " + lengths);
        if (lengths > Integer.MAX_VALUE) {
            // 文件太大，无法读取
            throw new IOException("File is to large " + file.getName());
        }
        // 创建一个数据来保存文件数据
        byte[] bytes = new byte[(int) lengths];// 读取数据到byte数组中
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }
        // 确保所有数据均被读取
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }
        // Close the input stream and return bytes
        is.close();
        return bytes;
    }

    public ResponseEntity<byte[]> getResponseEntity(HttpServletRequest request,Map<String,byte[]> map) throws IOException {
        HttpHeaders headers=new HttpHeaders();
        File file=null;
        String fileName=null;
        if(map.size()<=1){
            for (String str:map.keySet()){
                file=new File(request.getServletContext().getRealPath("/WEB-INF/office/"+str));
                fileName=str;
            }
        }else {
            try (FileOutputStream fileOutputStream = new FileOutputStream(request.getServletContext().getRealPath("/WEB-INF/office/indents.zip"))) {
                ZipOutputStream zipOutputStream=new ZipOutputStream(fileOutputStream);
                InputStream inputStream=null;
                for(String str:map.keySet()){
                    String path=request.getServletContext().getRealPath("/WEB-INF/office/"+str);
                    inputStream = new FileInputStream(path);
                    zipOutputStream.putNextEntry(new ZipEntry(str));
                    int temp=0;
                    while((temp = inputStream.read()) != -1){
                        zipOutputStream.write(temp);
                    }
                    inputStream.close();
                }
                zipOutputStream.close();
                file=new File(request.getServletContext().getRealPath("/WEB-INF/office/indents.zip"));
                fileName=new String("indents.zip".getBytes("utf-8"),"iso-8859-1");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(getBytesFromFile(file), headers, HttpStatus.CREATED);
        //删除文件释放服务器资源
        for (String str:map.keySet()){
            File tmp=new File(request.getServletContext().getRealPath("/WEB-INF/office/"+str));
            if(tmp.exists()){
                tmp.delete();
            }
        }
        if(fileName.equals("indents.zip")){
            File tmp=new File(request.getServletContext().getRealPath("/WEB-INF/office/indents.zip"));
            if(tmp.exists()){
                tmp.delete();
            }
        }
        return responseEntity;
    }
    public String generateExcel(HttpServletRequest request,String indentNum) throws IOException {
        String desPath=request.getServletContext().getRealPath("/WEB-INF/office/"+indentNum+".xlsx");
        //获取需要的数据
        IndentOutData indentOutData=indentOutDataDao.get(indentNum);
        if(indentOutData==null){
            return null;
        }
        Customer customer=customerDao.findById(indentOutData.getCustomerId());
        List<IndentDetailData> indentDetailDataList=indentDetailDao.getByIndentNum(indentNum);

        XSSFWorkbook workbook=PoiExcelTools.getWorkBook(desPath,indentOutData, customer, indentDetailDataList);

        FileOutputStream fileOutputStream=new FileOutputStream(desPath);
        workbook.write(fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        System.out.println(desPath);
        return desPath;
    }

}
