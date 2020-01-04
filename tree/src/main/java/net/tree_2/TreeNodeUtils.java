package net.tree_2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 树形结构生成工具
 */
@Slf4j
public class TreeNodeUtils {

    /**
     * 树形结构生成处理, vo对象将有Dto通过名称规范生成
     */
    public static <T extends TreeNode<T>, D> List<T> dtoListHandle(List<D> dtoList, Class<T> nodeClass) {
        return dtoListHandle(dtoList, null, nodeClass, null);
    }


    /**
     * 树形结构生成处理
     */
    public static <T extends TreeNode<T>, D> List<T> dtoListHandle(List<D> dtoList, Class<?> voClass, Class<T> nodeClass) {
        return dtoListHandle(dtoList, voClass, nodeClass, null);
    }

    /**
     * 树形结构生成处理, vo对象将有Dto通过名称规范生成
     */
    public static <T extends TreeNode<T>, D> List<T> dtoListHandle(List<D> dtoList, Class<T> nodeClass, Long pid) {
        return dtoListHandle(dtoList, null, nodeClass, pid);
    }

    /**
     * 树形结构生成处理
     */
    public static <T extends TreeNode<T>, D> List<T> dtoListHandle(List<D> dtoList, Class<?> voClass, Class<T> nodeClass, Long pid) {
        if (CollectionUtils.isEmpty(dtoList)) {
            return Collections.emptyList();
        }

        List<T> nodeList = new ArrayList<>();
        for (D dto : dtoList) {
            T node = convert(getVoByDto(dto, voClass), nodeClass);
            nodeList.add(node);

        }
        return treeNodeHandle(nodeList, pid);
    }

    /**
     * 树形结构生成处理
     */
    public static <T extends TreeNode<T>> List<T> treeNodeHandle(List<T> nodeList) {
        return treeNodeHandle(nodeList, null);
    }

    /**
     * 树形结构生成处理
     */
    public static <T extends TreeNode<T>> List<T> treeNodeHandle(List<T> nodeList, Long pid) {
        if (CollectionUtils.isEmpty(nodeList)) {
            return Collections.emptyList();
        }

        seqHandle(nodeList);
        childFindParentHandler(nodeList);

        List<T> resultList = new ArrayList<>();
        if (null == pid) {
            keepTrunk(resultList, nodeList, 1);
        } else {
            keepTrunk(resultList, nodeList, 1, pid);
        }
        return resultList;
    }

    /**
     * 保留树形结构主干
     */
    private static <T extends TreeNode<T>> List<T> keepTrunk(List<T> resultList,
                                                             List<T> nodeList, int level, long pid) {
        for (T node : nodeList) {
            if (null != node.getPid() && (pid == node.getPid().longValue() || level > 1)) {
                if (pid == node.getPid().longValue()) {
                    resultList.add(node);
                }
                node.setLevel(level);

                if (!CollectionUtils.isEmpty(node.getChildren())) {
                    keepTrunk(resultList, node.getChildren(), level + 1, pid);
                }
            }
        }
        return resultList;
    }

    /**
     * 保留树形结构主干
     */
    private static <T extends TreeNode<T>> List<T> keepTrunk(List<T> resultList,
                                                             List<T> nodeList, int level) {
        for (T node : nodeList) {
            if (null == node.getPid() || level > 1) {
                if (null == node.getPid()) {
                    resultList.add(node);
                }
                node.setLevel(level);

                if (!CollectionUtils.isEmpty(node.getChildren())) {
                    keepTrunk(resultList, node.getChildren(), level + 1);
                }
            }
        }
        return resultList;
    }

    /**
     * 子项关联到父项，就关联到父项下面
     */
    private static <T extends TreeNode<T>> void childFindParentHandler(List<T> nodeList) {
        for (T node : nodeList) {
            if (null != node.getPid()) {
                T pNode = getTreeNode(nodeList, node.getPid());
                if (null != pNode) {
                    pNode.addChild(node);
                }
            }
        }
    }

    /**
     * 获取项
     */
    private static <T extends TreeNode<T>> T getTreeNode(List<T> nodeList, long id) {
        for (T node : nodeList) {
            if (id == node.getId().longValue()) {
                return node;
            }
        }
        return null;
    }

    /**
     * 排序
     */
    private static <T extends TreeNode<T>> List<T> seqHandle(List<T> nodeList) {
        Collections.sort(nodeList, new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                if (o1.getSeq() > o2.getSeq()) {
                    return 1;
                }
                return -1;
            }
        });
        return nodeList;
    }

    /**
     * 获取vo对象
     */
    private static <D> Object getVoByDto(D dto, Class<?> voClass) {
        String voName = null;
        if (null != voClass) {
            voName = voClass.getSimpleName();
        } else {
            Class<? extends Object> dtoClass = dto.getClass();
            String simpleName = dtoClass.getSimpleName();
            voName = simpleName.substring(0, simpleName.length() - 3) + "Vo";
        }
        Method method = ClassUtils.getMethod(dto.getClass(), "get" + voName);
        try {
            return method.invoke(dto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T convert(Object source, Class<T> target) {
        if (source == null) {
            return null;
        }
        T targetObject = null;
        try {
            targetObject = target.newInstance();
            BeanUtils.copyProperties(source, targetObject);
        } catch (Exception e) {
            log.error("convert error ", e);
        }
        return targetObject;
    }
}