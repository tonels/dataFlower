package tonels.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "pis_user")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1054541215165L;
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    /**
     * 员工工号
     */
    @Column(name = "user_code")
    private String userCode;

    /**
     * 员工姓名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 登陆密码
     */
    @Column(name = "password_")
    private String password;

    /**
     * 联系电话
     */
    @Column(name = "phone_")
    private String phone;

    /**
     * 邮箱
     */
    @Column(name = "email_")
    private String email;

    /**
     * 所属部门
     */
    @Column(name = "department_id")
    private Long departmentId;

    /**
     * 部门名称
     */
    @Column(name = "department_name")
    private String departmentName;

    /**
     * 入职时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "entry_time")
    private LocalDate entryTime;

    /**
     * 离职时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "leave_time")
    private LocalDate leaveTime;

    /**
     * 就职状态
     * 0：离职；1：在职
     */
    @Column(name = "entrance_status")
    private Integer entranceStatus;

    /**
     * 所属公司
     */
    @Column(name = "company_")
    private String company;

    /**
     * 岗位
     */
    @Column(name = "station_")
    private String station;

    /**
     * 是否是领导
     * 0：不是，1：是
     */
    @Column(name = "leader_")
    private Integer leader;

    /**
     * 能否评定
     * 0：不能，1：能
     */
    @Column(name = "assess_")
    private Integer assess;

    /**
     * 修改人
     */
    @Column(name = "update_by")
    private String updateBy;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    @Column(name = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Transient
    private String roleNames;

}
