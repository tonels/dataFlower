package net.tree_2;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 授权资源树形结构类
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RscoTreeNode extends TreeNode<RscoTreeNode> {

    //资源路径
    private String url;
    //权限标识
    private String perm;
    //图标
    private String icon;
    //备注
    private String rmks;
    //分类编码
    private String catCd;
    //分类
    private String catNm;
    //状态编码
    private String statCd;
    //状态
    private String statNm;

}
