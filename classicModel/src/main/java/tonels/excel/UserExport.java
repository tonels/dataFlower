package tonels.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class UserExport {
    /**
     * 员工工号
     */
    @ExcelProperty("用户工号")
    private String userCode;
    /**
     * 员工姓名
     */
    @ExcelProperty("用户姓名")
    private String userName;
    /**
     * 部门名称
     */
    @ExcelProperty("部门")
    private String departmentName;
    /**
     * 入职时间
     */
    @ExcelProperty("入职时间")
    private String entryTime;
    /**
     * 就职状态
     * 0：离职；1：在职
     */
    @ExcelProperty("就职状态")
    private Integer entranceStatus;
    /**
     * 是否是领导
     * 0：不是，1：是
     */
    @ExcelProperty("是否领导")
    private Integer leader;
    /**
     * 能否评定
     * 0：不能，1：能
     */
    @ExcelProperty("是否评定人")
    private Integer assess;

    @ExcelProperty("更新时间")
    private String createTime;

}
