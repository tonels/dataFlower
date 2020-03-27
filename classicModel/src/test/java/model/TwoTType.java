package model;

import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import tonels.util.DateUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class TwoTType {
    private String tableName;
    private String field;
    private String fieldName;
    private String type;
    private String falseIs;
    private String comment;

    // 测试这种注解，也不可以直接转LocalDate/
//    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ymdTime ;
    private Date ymdhmsTime;

    // 这里测试直接用String 接收是可以的，但是这样的话，还需要String 转 时间类型
//    private String ymdTime;
//    private String ymdhmsTime;

}
