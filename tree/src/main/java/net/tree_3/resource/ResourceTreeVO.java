package net.tree_3.resource;

import lombok.Data;

import java.util.List;

@Data
public class ResourceTreeVO {

    private Long resourceId;
    /**
     * 资源级别
     * 1：一级,2：二级,3：三级
     */
    private Integer level;
    /**
     * 资源名称
     */
    private String name;
    /**
     * 父级ID
     */
    private Long parentId;
    /**
     * 权限标识
     */
    private String code;
    /**
     * 子列表
     */
    private List<ResourceTreeVO> children;

    public ResourceTreeVO(Resource resource) {
        this.resourceId = resource.getResourceId();
        this.level = resource.getLevel();
        this.name = resource.getName();
        this.parentId = resource.getParentId();
        this.code = resource.getCode();
        this.children = null;
    }

    public ResourceTreeVO() {
    }
}
