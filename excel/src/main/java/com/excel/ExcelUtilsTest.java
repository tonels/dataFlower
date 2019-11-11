package com.excel;

import com.excel.model.Contact;
import com.excel.model.Person;
import com.excel.model.User;
import com.excel.thread.ThreadUtil;
import com.excel.util.ExcelUtils;
import com.excel.util.ExcelUtils2;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

public class ExcelUtilsTest {
    private List<Person> persons;
    private List<User> users;
    private List<Contact> contacts;

    @Before
    public void setUp() {
        Person p1 = new Person("A", "a@roytuts.com", "Kolkata");
        Person p2 = new Person("B", "b@roytuts.com", "Mumbai");
        Person p3 = new Person("C", "c@roytuts.com", "Delhi");
        Person p4 = new Person("D", "d@roytuts.com", "Chennai");
        Person p5 = new Person("E", "e@roytuts.com", "Bangalore");
        Person p6 = new Person("F", "f@roytuts.com", "Hyderabad");
        Person p7 = new Person("F", "f@roytuts.com", "Hyderabad");
        Person p8 = new Person("F", "f@roytuts.com", "Hyderabad");
        Person p9 = new Person("F", "f@roytuts.com", "Hyderabad");
        Person p10 = new Person("F", "f@roytuts.com", "Hyderabad");
        Person p11 = new Person("F", "f@roytuts.com", "Hyderabad");
        Person p12 = new Person("F", "f@roytuts.com", "Hyderabad");
        Person p13 = new Person("F", "f@roytuts.com", "Hyderabad");
        persons = Lists.newArrayList(p1,
                p2,
                p3,
                p4,
                p5,
                p6,
                p7,
                p8,
                p9,
                p10,
                p11,
                p12,
                p13);

        User u1 = new User("u1", "pwd1");
        User u2 = new User("u2", "pwd2");
        User u3 = new User("u3", "pwd3");
        User u4 = new User("u4", "pwd4");
        User u5 = new User("u5", "pwd5");
        users = Lists.newArrayList(u1, u2, u3, u4, u5);

        Contact c1 = new Contact("9478512354", "24157853", "24578613");
        Contact c2 = new Contact("9478512354", "24157853", "24578613");
        Contact c3 = new Contact("9478512354", "24157853", "24578613");
        Contact c4 = new Contact("9478512354", "24157853", "24578613");
        contacts = Lists.newArrayList(c1, c2, c3, c4);

    }

    @Test
    public void testWriteToExcelInMultiSheets() {
        ExcelUtils.writeToExcelInMultiSheets("D:/excel.xlsx", "Person Details", persons);
        ExcelUtils.writeToExcelInMultiSheets("D:/excel.xlsx", "User Details", users);
        ExcelUtils.writeToExcelInMultiSheets("D:/excel.xlsx", "Contact Details", contacts);
    }

    @Test
    public void testBuildExcel() {

        LinkedHashMap<String, String> titleMap = Maps.newLinkedHashMap();

        titleMap.put("name", "姓名");
        titleMap.put("email", "邮箱");
        titleMap.put("address", "地址");

      ExcelUtils2.buildExcel(persons, 3, "任务信息表", "2019届Person信息表", titleMap);
    }

    @Test
    public void testSheetNum() {

        int i = ExcelUtils2.getSheetNum(persons, 3);
        System.out.println(i);

    }


    @Test
    public void testThread() {

        LinkedHashMap<String, String> titleMap = Maps.newLinkedHashMap();

        titleMap.put("name", "姓名");
        titleMap.put("email", "邮箱");
        titleMap.put("address", "地址");

        new Thread(new Runnable() {
            @Override
            public void run() {
                ExcelUtils2.buildExcel(persons, 3, "任务信息表", "2019届Person信息表", titleMap);
            }
        }).run();

        new Thread(new Runnable() {
            @Override
            public void run() {
                ExcelUtils2.buildExcel(persons, 3, "任务信息表", "2019届Person信息表", titleMap);
            }
        }).run();

    }


    @Test
    public void testThread2() {

//        for (int i = 0; i < 100; i++) {
//            Person p = new Person("name" + i, "email" + i, "address" + i);
//            persons.add(p);
//        }

//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName());
//                ExcelUtils.writeToExcelInMultiSheets("D://excel.xlsx", "sheet"+ Thread.currentThread().getName(), persons);
//            }
//        }).start();


        System.out.println(Thread.currentThread().getName());
        ExcelUtils.writeToExcelInMultiSheets("D:/excel.xlsx", "sheet"+ Thread.currentThread().getName(), persons);

    }

    @Test
    public void a1(){
        try {
            String filename = "D:/NewExcelFile.xls" ;
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("FirstSheet");

            HSSFRow rowhead = sheet.createRow((short)0);
            rowhead.createCell(0).setCellValue("No.");
            rowhead.createCell(1).setCellValue("Name");
            rowhead.createCell(2).setCellValue("Address");
            rowhead.createCell(3).setCellValue("Email");

            HSSFRow row = sheet.createRow((short)1);
            row.createCell(0).setCellValue("1");
            row.createCell(1).setCellValue("Sankumarsingh");
            row.createCell(2).setCellValue("India");
            row.createCell(3).setCellValue("sankumarsingh@gmail.com");

            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            System.out.println("Your excel file has been generated!");

        } catch ( Exception ex ) {
            System.out.println(ex);
        }
    }

    @Test
    public void createWorkbook(){
        File file = new File("d://found.xlsx");

        try {
            FileOutputStream fos = new FileOutputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook();

            XSSFSheet sheet1 = workbook.createSheet("fund1");
            XSSFSheet sheet2 = workbook.createSheet("fund2");
            XSSFSheet sheet3 = workbook.createSheet("fund3");

            Row row = sheet1.createRow(0);
            Cell cell0 = row.createCell(0);
            cell0.setCellValue("Nav Value");

            Cell cell1 = row.createCell(1);

            cell1.setCellValue("Amount Change");

            Cell cell2 = row.createCell(2);
            cell2.setCellValue("Percent Change");

            workbook.write(fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void thread1(){
        File file = new File("d://thread1.xlsx");

        new Thread(new Runnable() {
            @Override
            public void run() {

                String name = Thread.currentThread().getName();
                System.out.println(name);
                try {

                    FileOutputStream fos = new FileOutputStream(file);
                    XSSFWorkbook workbook = new XSSFWorkbook();

                    XSSFSheet sheet1 = workbook.createSheet(name);

                    Row row = sheet1.createRow(0);
                    Cell cell0 = row.createCell(0);
                    cell0.setCellValue("Nav Value");

                    Cell cell1 = row.createCell(1);

                    cell1.setCellValue("Amount Change");

                    Cell cell2 = row.createCell(2);
                    cell2.setCellValue("Percent Change");

                    workbook.write(fos);
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                String name = Thread.currentThread().getName();
                System.out.println(name);
                try {

                    FileOutputStream fos = new FileOutputStream(file);
                    XSSFWorkbook workbook = new XSSFWorkbook();

                    XSSFSheet sheet1 = workbook.createSheet(name);

                    Row row = sheet1.createRow(0);
                    Cell cell0 = row.createCell(0);
                    cell0.setCellValue("Navss Value");

                    Cell cell1 = row.createCell(1);

                    cell1.setCellValue("Amoussnt Change");

                    Cell cell2 = row.createCell(2);
                    cell2.setCellValue("Percssssent Change");

                    workbook.write(fos);
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void th1() throws InterruptedException {
        String filePath = "D:/NewExcelFile.xlsx" ;

        new Thread(new Athread(filePath,1)).start();
        new Thread(new Athread(filePath,2)).start();
        Thread.sleep(3000);

    }



//    @Test
//    public void s


    class Athread implements Runnable{

        private String filePath;
        private String name;
        private int id;

        public Athread(String filePath,int id) {
            this.filePath = filePath;
            this.id = id;
        }

        @Override
        public void run() {
            try {
                name = Thread.currentThread().getName();
                System.out.println("线程启动了" + Thread.currentThread().getName());
                FileOutputStream fos = new FileOutputStream(filePath);
                XSSFWorkbook workbook = new XSSFWorkbook();

                workbook.setSheetName(id,name);
                XSSFSheet sheet1 = workbook.getSheetAt(id);

                Row row = sheet1.createRow(0);
                Cell cell0 = row.createCell(0);
                cell0.setCellValue("Nav Value");

                Cell cell1 = row.createCell(1);

                cell1.setCellValue("Amount Change");

                Cell cell2 = row.createCell(2);
                cell2.setCellValue("Percent Change");

                workbook.write(fos);
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    public void thB() throws InterruptedException, IOException {
        String filename = "D:/ExcelB.xlsx" ;

        XSSFWorkbook workbook = new XSSFWorkbook();
        for (int i = 0; i < 5; i++) {
            workbook.createSheet("sheet"+ i);
        }

        FileOutputStream fos = new FileOutputStream(filename);
        fos.flush();
        fos.close();

        System.out.println(workbook.getSheetAt(0).getSheetName());

        // 这里执行会出错，因为 excel 并没有生成
        new Thread(new BThread(filename,1)).start();
        new Thread(new BThread(filename,3)).start();
        Thread.sleep(3000);

    }

    class BThread implements Runnable{

        private String filePath;
        private String name;
        private int id;

        public BThread(String filePath,int id) {
            this.filePath = filePath;
            this.id = id;
        }

        @Override
        public void run() {
            try {
                name = Thread.currentThread().getName();
                System.out.println("线程启动了" + Thread.currentThread().getName());
                FileOutputStream fos = new FileOutputStream(filePath);
                XSSFWorkbook workbook = new XSSFWorkbook();

                workbook.setSheetName(id,name);
                XSSFSheet sheet1 = workbook.getSheetAt(id);

                Row row = sheet1.createRow(0);
                Cell cell0 = row.createCell(0);
                cell0.setCellValue("Nav Value");

                Cell cell1 = row.createCell(1);

                cell1.setCellValue("Amount Change");

                Cell cell2 = row.createCell(2);
                cell2.setCellValue("Percent Change");

                workbook.write(fos);
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }







}
