package com.lkx.util;

import lombok.Data;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class ExcelParam implements Serializable {

    private static final long serialVersionUID = -4231868339831975335L;
    //文件地址,本地读取时用
    private String filePath;
    //反射具体类
    private Class clazz;
    //从第几行开始扫描
    private Integer rowNumIndex;
    //读取到第几个sheet结束
    private Integer sheetIndex;
    //存储属性和表头的对应关系
    private Map map;
    //keyValue
    private String keyValue;
    //表头是否强一致
    private Boolean sameHeader = false;
    //是否流读取
    private Boolean stream = false;
    //用流代替本地文件
    private byte[] buf;
    //输出流
    private HttpServletResponse response;
    //文件名
    private String fileName;
    //文件输出路径
    private String outFilePath;
    //文件导出封装数据
    private List list;

    public ExcelParam() {
    }

    public ExcelParam(Class clazz, String keyValue, String outFilePath, List list) {
        this.clazz = clazz;
        this.keyValue = keyValue;
        this.outFilePath = outFilePath;
        this.list = list;
    }

    public ExcelParam(Class clazz, String outFilePath, List list) {
        this.clazz = clazz;
        this.outFilePath = outFilePath;
        this.list = list;
    }

    public ExcelParam(Class clazz, HttpServletResponse response, List list) {
        this.clazz = clazz;
        this.response = response;
        this.list = list;
    }

    public ExcelParam(Class clazz, java.lang.String keyValue, HttpServletResponse response, List list) {
        this.clazz = clazz;
        this.keyValue = keyValue;
        this.response = response;
        this.list = list;
    }

    public ExcelParam(Class clazz, java.lang.String keyValue, HttpServletResponse response, java.lang.String fileName, List list) {
        this.clazz = clazz;
        this.keyValue = keyValue;
        this.response = response;
        this.fileName = fileName;
        this.list = list;
    }

    public ExcelParam(Class clazz, HttpServletResponse response, java.lang.String fileName, List list) {
        this.clazz = clazz;
        this.response = response;
        this.fileName = fileName;
        this.list = list;
    }

}
