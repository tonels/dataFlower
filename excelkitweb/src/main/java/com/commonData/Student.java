package com.commonData;

import com.wuwenze.poi.annotation.Excel;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import java.util.Date;

@Data
@Excel("学生信息")
public class Student {

    @ExcelField(value = "编号", width = 30)
    private Integer studentId;

    @ExcelField(//
            value = "学生姓名",//
            required = true,//
            comment = "请填写学生姓名，最大长度为12，且不能重复"
    )
    private String name;

    @ExcelField(
            value = "性别",//
            readConverterExp = "未知=0,男=1,女=2",//
            writeConverterExp = "0=未知,1=男,2=女",//
            options = SexOptions.class//
    )
    private Integer sex;

    @ExcelField(value = "创建时间", dateFormat = "yyyy/MM/dd HH:mm:ss")
    private Date createAt;

    public Student(Integer studentId, String name, Integer sex, Date createAt) {
        this.studentId = studentId;
        this.name = name;
        this.sex = sex;
        this.createAt = createAt;
    }
}