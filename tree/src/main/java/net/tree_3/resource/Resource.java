package net.tree_3.resource;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
public class Resource implements Serializable {

    private static final long serialVersionUID = 23412515165L;
    /**
     * 主键ID
     */
    private Long resourceId;
    /**
     * 资源级别
     * 1：一级,2：二级,3：三级
     */
    private Integer level;
    /**
     * 父级ID
     */
    private Long parentId;
    /**
     * 资源名称
     */
    private String name;
    /**
     * 描述
     */
    private String summary;
    /**
     * 权限标识
     */
    private String code;
    /**
     * 修改人
     */
    private String updateBy;
    /**
     * 修改时间
     */
    private LocalDateTime updateTime;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    private List<Resource> children;

}
