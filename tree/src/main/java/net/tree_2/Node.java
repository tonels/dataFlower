package net.tree_2;

import lombok.Data;

import java.io.Serializable;

/**
 * 节点
 */
@Data
public class Node implements Serializable {
    private static final long serialVersionUID = -3514061958234921653L;
    /**
     * id
     */
    private Long id;
    /**
     * 名称
     */
    private String nm;

}

