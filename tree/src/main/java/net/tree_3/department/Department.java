package net.tree_3.department;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 部门表
 */
@Data
public class Department implements Serializable {
    private static final long serialVersionUID = 6296251349484778310L;
    /**
     * 主键ID
     */
    private Long departmentId;
    /**
     * 部门名称
     */
    private String departmentName;
    /**
     * 部门级别	INT	0：公司
     * 1：一级部门
     * 2：二级部门
     * 3：三级部门
     */
    private Integer level;
    /**
     * 父ID
     */
    private Long parentId;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 子节点
     */
    private List<Department> child;
}