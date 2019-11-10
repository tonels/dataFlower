package com.excel.util;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;

// 这里我封装，
public class ExcelUtils3 {

    /**
     * @param <T>
     * @since
     * @param dataList 数据List
     * @param rowMaxCount 每个sheet最大记录条数
     * @param fileName 文件名
     * @param sheetTitle sheet名
     * @param sheetTh 操作第几个Sheet
     * @param titleMap 表格头
     */
    public static <T> void buildExcel(List<T> dataList, int rowMaxCount, String fileName, String sheetTitle,int sheetTh, LinkedHashMap<String,String> titleMap){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMDDhhmmss");
            String now = dateFormat.format(new Date());
            //导出文件路径
            String basePath = "D:/";
            //文件名
            String exportFileName = fileName+"_"+now+".xlsx";

            // 声明一个工作薄
            XSSFWorkbook workBook = null;
            workBook = new XSSFWorkbook();

            int sheetCount = getSheetNum(dataList, rowMaxCount);

            // 拆分大的List为多个小的List
            List<List<T>> splitList = null;
            if (dataList != null && !dataList.isEmpty()) {
                splitList = getSplitList(dataList, rowMaxCount, sheetCount);
            } else {
                throw new Exception("源数据不存在");
            }


            new Runnable(){
                @Override
                public void run() {

                }
            }.run();

            //循环dataList 看需要生成几个sheet
            for(int i=0;i<splitList.size();i++){
                // 生成一个表格
                XSSFSheet sheet = workBook.createSheet();
                workBook.setSheetName(i,"学生信息_"+(i+1));
                //最新Excel列索引,从0开始
                int lastRowIndex = sheet.getLastRowNum();
                if (lastRowIndex > 0) {
                    lastRowIndex++;
                }
                if(sheetTitle!=null){
                    // 合并单元格
                    //参数：起始行号，终止行号， 起始列号，终止列号
                    //CellRangeAddress（int firstRow, int lastRow, int firstCol, int lastCol）
                    sheet.addMergedRegion(new CellRangeAddress(lastRowIndex, lastRowIndex, 0, titleMap.size()));
                    // 产生表格标题行
                    XSSFCell cellMerged= sheet.createRow(lastRowIndex).createCell(lastRowIndex);
                    cellMerged.setCellValue(new XSSFRichTextString(sheetTitle));
                    lastRowIndex++;
                }
                // 创建表格列标题行
                XSSFRow titleRow = sheet.createRow(lastRowIndex);
                Iterator<String> colIteratorV=titleMap.values().iterator();
                int h = 0;
                while(colIteratorV.hasNext()){
                    Object value = colIteratorV.next();
                    titleRow.createCell(h).setCellValue(value.toString());
                    h++;
                }
                //插入需导出的数据
                Class<? extends Object> clazz = null;
                List<T> subList = new ArrayList<T>();
                subList = splitList.get(i);
                for(int j=0;j<subList.size();j++){
                    clazz = subList.get(0).getClass();
                    XSSFRow row = sheet.createRow(j+lastRowIndex+1);
                    Iterator<String> colIteratorK=titleMap.keySet().iterator();
                    int k = 0;
                    while(colIteratorK.hasNext()){
                        Object key = colIteratorK.next();
                        Method method = clazz.getMethod(getMethodName(key.toString()));
                        Object obj = method.invoke(subList.get(j));
                        row.createCell(k).setCellValue(obj==null?"":obj.toString());
                        k++;
                    }
                }

            }
            File  file = new File(basePath+exportFileName);
            //文件输出流
            FileOutputStream outStream = new FileOutputStream(file);
            workBook.write(outStream);
            outStream.flush();
            outStream.close();
            System.out.println("导出2007文件成功！文件导出路径：--"+basePath+exportFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 分割list
     * @param dataList  数据源
     * @param rowMaxCount 每个sheet最大记录条数
     * @param sheetCount 需要分多少个sheet
     * @return
     */
    public static <T> List<List<T>> getSplitList(List<T> dataList, int rowMaxCount,
                                                 int sheetCount) {
        List<List<T>> subList = new ArrayList<List<T>>();
        for (int i = 1; i <= sheetCount; i++) {
            if (i == 1) {
                // 第一个list
                if(dataList.size()>=rowMaxCount){
                    subList.add(dataList.subList(0, rowMaxCount));
                }else{
                    subList.add(dataList.subList(0, dataList.size()));
                }
            } else if (i == sheetCount) {
                // 最后一个list
                subList.add(dataList.subList((sheetCount - 1) * rowMaxCount, dataList.size()));
            } else {
                subList.add(dataList.subList((i - 1) * rowMaxCount , i * rowMaxCount));
            }
        }
        return subList;
    }


    /**
     * 获取方法名
     * @param
     * */
    private static String getMethodName(String fieldName){
        return "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
    }

    /**
     * 计算需要的 sheet 数
     * @param dataList 数据源
     * @param rowMaxCount 每个sheet最大行数
     * @return sheet 数
     */
    public static <T> int getSheetNum(List<T> dataList, int rowMaxCount){
        // 获取数据总条数
        int count = dataList.size();
        // 需要分多少个sheet
        return count % rowMaxCount >= 1 ? count / rowMaxCount + 1 : count / rowMaxCount;
    }


    protected static class PrThread implements Runnable {
        private final CountDownLatch doneSignal;
        private int sheet;

        public PrThread(CountDownLatch doneSignal, int sheet) {
            this.doneSignal = doneSignal;
            this.sheet = sheet;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());

            doneSignal.countDown();
            System.out.println("执行后Count剩余" + doneSignal.getCount());
        }
    }




}

