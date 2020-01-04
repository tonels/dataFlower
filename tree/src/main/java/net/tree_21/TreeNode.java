package net.tree_21;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 树形接口节点
 */
@Data
@Accessors(chain = true)
public class TreeNode<T> implements Serializable {
    private static final long serialVersionUID = -8071463452448530550L;
    /**
     * id
     */
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 父id
     */
    private Long pid;
    /**
     * 排序
     */
    private int seq;
    /**
     * 层级
     */
    private int level;
    /**
     * 子项集合
     */
    private List<T> children;

    public void addChild(T node) {
        if (null == children) {
            children = new ArrayList<>();
        }
        children.add(node);
    }

}
