package net.tree_3.resource;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

/**
 * 根据资源菜单Model,构建 tree 型结构
 */
public class ResourceTreeUtil {

    private List<ResourceTreeVO> rootList; //根节点对象存放到这里
    private List<ResourceTreeVO> bodyList; //其他节点存放到这里

    public ResourceTreeUtil(List<ResourceTreeVO> rootList, List<ResourceTreeVO> bodyList) {
        this.rootList = rootList;
        this.bodyList = bodyList;
    }

    // 构建Tree方法调用
    public List<ResourceTreeVO> getTree() {
        if (bodyList != null && !bodyList.isEmpty()) {
            // 声明 map，用来过滤已操作过的数据
            Map<Long, Long> map = Maps.newHashMapWithExpectedSize(bodyList.size());
            rootList.forEach(beanTree -> getChild(beanTree, map));
            return rootList;
        }
        return null;
    }

    // 递归实现获取子集
    public void getChild(ResourceTreeVO beanTree, Map<Long, Long> map) {
        List<ResourceTreeVO> childList = Lists.newArrayList();
        bodyList.stream()
                .filter(c -> !map.containsKey(c.getResourceId()))
                .filter(c -> c.getParentId().equals(beanTree.getResourceId()))
                .forEach(c -> {
                    map.put(c.getResourceId(), c.getParentId());
                    getChild(c, map);
                    childList.add(c);
                });
        beanTree.setChildren(childList);
    }

}