package com.ttt.mim.common.utils;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class PoiWordTools {

    public static Boolean changeWord(String srcPath, String destPath, Map<String, String> map, List<String[]> tableList) {
        try {
            InputStream docis = new FileInputStream(srcPath);
            CustomXWPFDocument document = new CustomXWPFDocument(docis);
            // 替换段落中的指定文字
            Iterator<XWPFParagraph> itPara = document.getParagraphsIterator();
            while (itPara.hasNext()) {
                XWPFParagraph paragraph = (XWPFParagraph) itPara.next();
                String paraText=paragraph.getText();
                Set<String> set = map.keySet();
                Iterator<String> iterator = set.iterator();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    List<XWPFRun> run=paragraph.getRuns();
                    for(int i=0;i<run.size();i++)
                    {
                        if(run.get(i).getText(run.get(i).getTextPosition())!=null && run.get(i).getText(run.get(i).getTextPosition()).trim().equals(key))
                        {
                            /**参数0表示生成的文字是要从哪一个地方开始放置,设置文字从位置0开始
                             * 就可以把原来的文字全部替换掉了
                             * */
                            run.get(i).setText(map.get(key),0);
                        }
                    }
                }
            }

            // 替换表格中的指定文字=
            List<XWPFTable> tables = document.getTables();
            for (int i=0;i<tables.size();i++){
                XWPFTable table = tables.get(i);
                if(i<tables.size()-1){
                    int rcount = table.getNumberOfRows();
                    for (int j = 0; j < rcount; j++) {
                        XWPFTableRow row = table.getRow(j);
                        List<XWPFTableCell> cells = row.getTableCells();
                        for (XWPFTableCell cell : cells) {
                            for (Entry<String, String> e : map.entrySet()) {
                                if (cell.getText().equals(e.getKey())) {
                                    cell.removeParagraph(0);
                                    cell.setText(e.getValue());
                                }
                            }
                        }
                    }
                }else {
                    if(table.getRows().size()>1){
                        //判断表格是需要替换还是需要插入，判断逻辑有#为替换，表格无#为插入
                        insertTable(table, tableList);
                    }
                }

            }
            //向表格中插入数据


//            InputStream is = new FileInputStream("C:/Users/Administrator/Desktop/img1.png");
//            String blipId = document.addPictureData(is, CustomXWPFDocument.PICTURE_TYPE_PNG);
//            document.createPicture(blipId,document.getNextPicNameNumber(CustomXWPFDocument.PICTURE_TYPE_PNG), 500, 500);
            FileOutputStream outStream = null;
            outStream = new FileOutputStream(destPath);
            document.write(outStream);
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }
    public static void insertTable(XWPFTable table, List<String[]> tableList){
        if(tableList==null || tableList.size()<=0){
            return;
        }
        //创建行,根据需要插入的数据添加新行，不处理表头
        for(int i = 1; i < tableList.size(); i++){
            XWPFTableRow row =table.createRow();
        }
        //遍历表格插入数据
        List<XWPFTableRow> rows = table.getRows();
        for(int i = 1; i < rows.size(); i++){
            XWPFTableRow newRow = table.getRow(i);
            List<XWPFTableCell> cells = newRow.getTableCells();
            for(int j = 0; j < cells.size(); j++){
                XWPFTableCell cell = cells.get(j);
                cell.setText(tableList.get(i-1)[j]);
            }
        }

    }
}