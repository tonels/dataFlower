package com.util;

import com.commonData.Student;
import com.commonData.User;
import com.google.common.collect.Maps;
import com.wuwenze.poi.pojo.ExcelErrorField;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUtil {
    public static Map<String, Object> newHashMap(String sheetIndex,
                                                 int sheetIndex1,
                                                 String rowIndex,
                                                 int rowIndex1,
                                                 String errorFields,
                                                 List<ExcelErrorField> errorFields1) {
        HashMap<String, Object> hashMap = Maps.newHashMap();
        hashMap.put("sheetIndex",sheetIndex);
        hashMap.put("sheetIndex1",sheetIndex1);
        hashMap.put("rowIndex",rowIndex);
        hashMap.put("rowIndex1",rowIndex1);
        hashMap.put("errorFields",errorFields);
        return hashMap;
    }

//    public static Map<String, Object> newHashMap(String data,
//                                                 List<User> successList,
//                                                 String haveError,
//                                                 boolean b,
//                                                 String error,
//                                                 List<Map<String, Object>> errorList,
//                                                 String timeConsuming,
//                                                 long l) {
//        HashMap<String, Object> hashMap = Maps.newHashMap();
//        hashMap.put("data",data);
//        hashMap.put("successList",successList);
//        hashMap.put("haveError",haveError);
//        hashMap.put("b",b);
//        hashMap.put("error",error);
//        hashMap.put("errorList",errorList);
//        hashMap.put("timeConsuming",timeConsuming);
//        hashMap.put("l",l);
//        return hashMap;
//    }

    public static Map<String, Object> newHashMap(String data,
                                                 List<Student> successList,
                                                 String haveError,
                                                 boolean b,
                                                 String error,
                                                 List<Map<String, Object>> errorList,
                                                 String timeConsuming,
                                                 long l) {
        HashMap<String, Object> hashMap = Maps.newHashMap();
        hashMap.put("data",data);
        hashMap.put("successList",successList);
        hashMap.put("haveError",haveError);
        hashMap.put("b",b);
        hashMap.put("error",error);
        hashMap.put("errorList",errorList);
        hashMap.put("timeConsuming",timeConsuming);
        hashMap.put("l",l);
        return hashMap;
    }
}
