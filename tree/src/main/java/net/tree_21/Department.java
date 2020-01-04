package net.tree_21;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 授权资源树形结构类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Department extends TreeNode<Department> {

    // 部门路径
    private String url;

}
