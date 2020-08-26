import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ttt.mim.dao.IndentDao;
import com.ttt.mim.domain.Indent;
import com.ttt.mim.domain.Quote;
import com.ttt.mim.service.IndentService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-mybatis.xml")
public class IndentTest {

    @Resource
    private IndentDao indentDao;
    @Resource
    private IndentService indentService;

    @Test
    public void Dao(){

        /**
         * 测试indentDao.get
         */
        /*System.out.println(indentDao.get("1908160001"));*/

        /**
         * 测试indentDao.add
         */
        /*Indent indent=new Indent(null,indentService.getAvailableIndentNum(),"测试8160001",1,1,8872.0,10000.0,"备注8160001", new Date(),new Date());
        System.out.println(indentDao.add(indent));
        System.out.println(indent);*/

        /**
         * 批量添加数据用于后续测试
         */
        /*int num=190816;
        for (int i = 0; i < 100; i++) {
            String randomStr="aksjbflaksbdabdfALSJBALKSJVBAFlkhbLIDVlihvdHVDFIhvdfHDVFLhdbf";
            String numStr=num+String.format("%04d", i+1);
            int startIndex=(int)(Math.random()*(randomStr.length()-6));
            String name="NAME-"+randomStr.substring(startIndex,startIndex+5)+"-"+numStr;
            String remark="REMARK-"+randomStr.substring(startIndex,startIndex+5)+"-"+numStr;
            Double costMoney=(double) (((int)((Math.random()*1000000+100000)*100)))/100;
            Double quoteMoney=(double)((int)(costMoney*1.4*100))/100;
            Indent indent=new Indent(null, numStr, name, 1, 1, costMoney, quoteMoney, remark, new Date(), new Date());
            indentDao.add(indent);
            System.out.println(indent);

        }*/

        /**
         * 测试indentDao.update
         */
        /*Indent indent=new Indent(null, "1908160001", "修改名称1908160001", "修改备注1908160001", new Date());
        System.out.println(indentDao.update(indent));
        System.out.println(indentDao.get(indent.getNum()));*/

        /**
         * 测试indentDao.query
         */
        Map<String,Object> map=new HashMap<>();
//        map.put("num", "190816000");
//        map.put("name","09");
        List<Indent> indents=indentDao.query(map);
        for (Indent indent :indents)
            System.out.println(indent);
        /**
         * 测试indentDao.delete
         */
        /*System.out.println(indentDao.get("1908160001"));
        System.out.println(indentDao.delete("1908160001"));
        System.out.println(indentDao.get("1908160001"));*/
        /**
         * 测试indentDao.batchDelete
         */
        /*Map<String,Object> map=new HashMap<>();
        map.put("num", "190816001");
        List<Indent> indents=indentDao.query(map);
        List<String> indentNUms=new ArrayList<>();
        for (Indent indent :indents)
            indentNUms.add(indent.getNum());
        String[] nums=new String[indentNUms.size()];
        indentNUms.toArray(nums);
        System.out.println(indentDao.batchDelete(nums));*/
        /**
         * 测试indentDao.getTodayMaxIndentNum
         */
        /*Date now=new Date();
        now.setDate(16);
        String year=now.getYear()%100+"";
        String month="";
        if((now.getMonth()+1)<10){
            month += "0"+(now.getMonth()+1);
        }else {
            month += (now.getMonth()+1);
        }
        String day="";
        if(now.getDate()<10){
            day += "0"+now.getDate();
        }else {
            day += now.getDate();
        }
        String todayNumStr=year+month+day;
        System.out.println(todayNumStr);
        System.out.println(indentDao.getTodayMaxIndentNum(todayNumStr));*/
    }
    @Test
    public void Service(){
        /**
         * 准备json数据
         */
        /*JSONObject jsonObject=new JSONObject();
        Indent indent = new Indent(null, null, "测试indentService", 1, 1, 451820.0, 600000.0, "测试备注", null, null);
        jsonObject.put("indent", indent);

        Quote quote = new Quote(null, null, 12, 3460.0, null);
        Quote quote2 = new Quote(null, null, 13, 1111.0, null);
        JSONArray quoteArray=new JSONArray();
        quoteArray.add(quote);
        quoteArray.add(quote2);
        jsonObject.put("quotes", quoteArray);
        System.out.println(jsonObject);*/
        /**
         * indentService.add(JSONobject data)
         */
        /*JSONObject jsonObject=JSONObject.parseObject("{\"indent\":{\"costMoney\":451820.0,\"customerId\":1,\"name\":\"测试indentService\",\"quoteMoney\":600000.0,\"remark\":\"测试备注\",\"userId\":1},\"quotes\":[{\"mId\":1,\"quote\":3460.0},{\"mId\":1,\"quote\":1111.0}]}");
        indentService.add(jsonObject);*/
        /**
         * update(Indent indent)
         */
        /*Indent indent = new Indent("201908200000017", "订单测试修改", "bz", new Date());
        indentService.update(indent);*/
        /**
         * delete
         */
        /*indentService.delete("201908200000013");*/
        /*String[] indentNums=new String[]{"1908160100","1908160099","201908200000008"};
        indentService.batchDelete(indentNums);*/
        /*System.out.println(indentService.get("1908160098"));*/
    }

}
