package com.ttt.mim.common.utils;

import com.ttt.mim.domain.Customer;
import com.ttt.mim.domain.IndentDetailData;
import com.ttt.mim.domain.IndentOutData;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;


public class PoiExcelTools {
    public static XSSFWorkbook getWorkBook(String desPath,IndentOutData indentOutData, Customer customer,List<IndentDetailData> indentDetailDataList){
        //创建表格
        XSSFWorkbook workbook=new XSSFWorkbook();
        Sheet sheet=workbook.createSheet("订单详情");
        //创建单元格
        createCells(workbook, 1, 13+indentDetailDataList.size(), 1, 6);
        //先创建一个空行
        Row row0=sheet.getRow(0);
        //创建订单信息标题行
        Row row1=sheet.getRow(1);
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 6));
        Cell cell_11=row1.getCell(1);
        cell_11.setCellValue("订单信息");
        CellStyle styleCell_11=cell_11.getCellStyle();
        styleCell_11.setAlignment(CellStyle.ALIGN_CENTER);
        cell_11.setCellStyle(styleCell_11);
        //创建第三行
        Row row2=sheet.getRow(2);
        Cell cell_21=row2.getCell(1);
        cell_21.setCellValue("订单编号：");
        Cell cell_23=row2.getCell(3);
        cell_23.setCellValue("订单名称：");
        Cell cell_25=row2.getCell(5);
        cell_25.setCellValue("总成本价：");
        //创建第四行
        Row row3=sheet.getRow(3);
        Cell cell_31=row3.getCell(1);
        cell_31.setCellValue("订单创建人：");
        Cell cell_33=row3.getCell(3);
        cell_33.setCellValue("客户名称：");
        Cell cell_35=row3.getCell(5);
        cell_35.setCellValue("总报价：");
        //创建(订单创建时间)第五行
        Row row4=sheet.getRow(4);
        sheet.addMergedRegion(new CellRangeAddress(4, 4, 1, 6));
        Cell cell_41=row4.getCell(1);
        cell_41.setCellValue("订单创建时间");
        CellStyle styleCell_41=cell_41.getCellStyle();
        styleCell_41.setAlignment(CellStyle.ALIGN_CENTER);
        cell_41.setCellStyle(styleCell_41);
        //创建(订单修改时间)第六行
        Row row5=sheet.getRow(5);
        sheet.addMergedRegion(new CellRangeAddress(5, 5, 1, 6));
        Cell cell_51=row5.getCell(1);
        cell_51.setCellValue("订单最后修改时间");
        CellStyle styleCell_51=cell_51.getCellStyle();
        styleCell_51.setAlignment(CellStyle.ALIGN_CENTER);
        cell_51.setCellStyle(styleCell_51);
        System.out.println(sheet);

        //创建第七行空行
        Row row6=sheet.getRow(6);
        //创建第八行
        Row row7=sheet.getRow(7);
        sheet.addMergedRegion(new CellRangeAddress(7, 7, 1, 6));
        Cell cell_71=row7.getCell(1);
        cell_71.setCellValue("客户信息");
        CellStyle styleCell_71=cell_71.getCellStyle();
        styleCell_71.setAlignment(CellStyle.ALIGN_CENTER);
        cell_71.setCellStyle(styleCell_71);
        //创建第九行
        Row row8=sheet.getRow(8);
        Cell cell_81=row8.getCell(1);
        cell_81.setCellValue("客户名称：");
        sheet.addMergedRegion(new CellRangeAddress(8, 8, 2, 6));
        //创建第十行
        Row row9=sheet.getRow(9);
        Cell cell_91=row9.getCell(1);
        cell_91.setCellValue("法人：");
        Cell cell_93=row9.getCell(3);
        cell_93.setCellValue("联系方式：");
        Cell cell_95=row9.getCell(5);
        cell_95.setCellValue("状态：");
        //创建第十一行
        Row row10=sheet.getRow(10);
        Cell cell_101=row10.getCell(1);
        cell_101.setCellValue("行业：");
        Cell cell_103=row10.getCell(3);
        cell_103.setCellValue("统一社会信用代码：");
        Cell cell_105=row10.getCell(5);
        cell_105.setCellValue("信用情况：");
        //创建第十二行空行
        Row row11=sheet.getRow(11);
        //创建第十三行
        Row row12=sheet.getRow(12);
        sheet.addMergedRegion(new CellRangeAddress(12, 12, 1, 6));
        Cell cell_121=row12.getCell(1);
        cell_121.setCellValue("物资信息");
        CellStyle styleCell_121=cell_121.getCellStyle();
        styleCell_121.setAlignment(CellStyle.ALIGN_CENTER);
        cell_121.setCellStyle(styleCell_121);
        //创建第十四行
        String[] header={"物资名称","厂商","规格","数量","报价","小计"};
        Row row13=sheet.getRow(13);
        for (int i=1;i<7;i++){
            Cell cell=row13.getCell(i);
            cell.setCellValue(header[i-1]);
        }
        insertData(workbook, indentOutData, customer, indentDetailDataList);
        return workbook;
    }
    public static void insertData(XSSFWorkbook workbook, IndentOutData indentOutData, Customer customer,List<IndentDetailData> indentDetailDataList){
        Sheet sheet=workbook.getSheetAt(0);
        update(workbook,2, 2, indentOutData.getIndentNum());
        update(workbook,2, 4, indentOutData.getIndentName());
        update(workbook,2, 6,indentOutData.getCostMoney().toString());
        update(workbook,3,  2,indentOutData.getCreatorName());
        update(workbook,3, 4,indentOutData.getCustomerName());
        update(workbook,3,  6,indentOutData.getQuoteMoney().toString());
        Instant instant=Instant.ofEpochMilli(indentOutData.getCreateTime().getTime());
        LocalDateTime cre=LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        update(workbook,4,  1,"订单创建时间："+cre.toLocalDate() +" "+ cre.toLocalTime());
        instant=Instant.ofEpochMilli(indentOutData.getModifyTime().getTime());
        cre=LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        update(workbook,5,  1,"订单最后修改时间："+cre.toLocalDate() +" "+ cre.toLocalTime());

        update(workbook,8, 2 ,customer.getName());
        update(workbook,9, 2, customer.getLegalPerson());
        update(workbook,9,4 , customer.getPhone());
        update(workbook,9, 6, customer.getStatus());
        update(workbook,10,2 , customer.getIndustry());
        update(workbook,10, 4, customer.getSocialCode());
        update(workbook,10,6 , customer.getCredit());


        int count=14;
        for (IndentDetailData data:indentDetailDataList){
            String[] datas={data.getMName(),data.getFactoryName(),data.getSpec(),data.getNumber().toString(),data.getQuote().toString(), data.getSum().toString()};
            for (int i=1;i<7;i++){
                update(workbook,count, i, datas[i-1]);
            }
            count++;
        }

    }
    private static void update(XSSFWorkbook workbook,int row,int col,String value){
        Sheet sheet=workbook.getSheetAt(0);
        Cell cell=sheet.getRow(row).getCell(col);
        cell.setCellValue(value);
    }
    private static void createCells(XSSFWorkbook workbook,int firstRow, int lastRow, int firstCol, int lastCol){
        Sheet sheet=workbook.getSheetAt(0);
        for (int rowNum=firstRow;rowNum<=lastRow;rowNum++){
            Row row=sheet.createRow(rowNum);
            for (int colNum=firstCol;colNum<=lastCol;colNum++){
                Cell cell=row.createCell(colNum);
                CellStyle style=workbook.createCellStyle();
                if(rowNum!=6 && rowNum!=11){
                    style.setBorderTop((short) 1);
                    style.setBorderBottom((short) 1);
                    style.setBorderLeft((short) 1);
                    style.setBorderRight((short) 1);
                    cell.setCellStyle(style);
                }

            }
        }
        for (int i=1;i<7;i++){
            sheet.setColumnWidth(i,256*16+184);
        }
    }
//    public static XSSFWorkbook setOutBorder(XSSFWorkbook workBook, Set<BorderType> borderTypes, int firstRow, int lastRow, int firstCol, int lastCol) throws IOException {
//        Sheet sheet=workBook.getSheetAt(0);
//        for (int rowNum=firstRow;rowNum<=lastRow;rowNum++){
//            Row row=sheet.getRow(rowNum);
//            for (int colNumber = firstCol; colNumber <= lastCol; colNumber++) {
//                Cell cell=row.getCell(colNumber);
//                if (cell==null){
//                    cell=row.createCell(colNumber);
//                }
//                CellStyle cellStyle=cell.getCellStyle();
//                System.out.println(cellStyle);
//                if (rowNum==firstRow){
//                    if(borderTypes.contains(BorderType.TOP))
//                        cellStyle.setBorderTop((short) 1);
//                }
//                if(rowNum==lastRow){
//                    if(borderTypes.contains(BorderType.BOTTOM))
//                        cellStyle.setBorderBottom((short) 1);
//                }
//                if(colNumber==firstCol){
//                    if(borderTypes.contains(BorderType.LEFT))
//                        cellStyle.setBorderLeft((short) 1);
//                }
//                if (colNumber==lastCol){
//                    if(borderTypes.contains(BorderType.RIGHT))
//                        cellStyle.setBorderRight((short) 1);
//                }
//                cell.setCellStyle(cellStyle);
//            }
//        }
//        FileOutputStream fileOutputStream=new FileOutputStream("E:\\cuit_study\\SummerCamp201907\\MIM\\MIM\\out\\artifacts\\MIM_war_exploded\\WEB-INF\\office\\1.xlsx");
//        workBook.write(fileOutputStream);
//        fileOutputStream.flush();
//        fileOutputStream.close();
//        return workBook;
//
//    }
//    enum BorderType{
//        TOP,
//        LEFT,
//        BOTTOM,
//        RIGHT
//    }
}
