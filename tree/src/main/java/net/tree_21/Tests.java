package net.tree_21;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.google.common.io.CharStreams;
import net.tree_2.TreeNodeUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.CollectionUtils;
import sun.rmi.rmic.iiop.ClassPathLoader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.*;

public class Tests {

    private List<Department> list;

    @Before
    public void setUp() throws Exception {
        try {
            // 根据resource文件路径，生成文件
            ClassPathResource resource = new ClassPathResource("db/list.json");
            // 解析文件为指定编码的字符串
            String string = CharStreams.toString(new InputStreamReader(resource.getInputStream(), "UTF-8"));
            list = JSONArray.parseArray(string, Department.class);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Test
    public void t1(){
//        List<Department> list = Lists.newArrayList();
        List<Department> departments = TreeUtils.treeNodeHandle(list,-1L);
    }

    @Test
    public void t2(){
//        List<Department> list = Lists.newArrayList();
        List<Department> departments = TreeUtils.treeNodeHandle(list,1L);
        System.out.println(JSON.toJSONString(departments));
    }

    @Test
    public void t3(){
        TreeUtils.childFindParentHandler(list);
    }


    /**
     *
     * @param id 树节点
     * @param pidColumn 表示数据库父类主键
     */
//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public void deleteTreeById(Serializable id, String pidColumn) {
//        TableInfo tableInfo = TableInfoHelper.getTableInfo(currentModelClass());
//
//        Map<String, Object> columnMap = new HashMap<>();
//        columnMap.put(pidColumn, id);
//        List<Map<String, Object>> listMaps = this.listMapsByMap(columnMap);
//        if (CollectionUtils.isNotEmpty(listMaps)) {
//            for (Map<String, Object> map : listMaps) {
//                deleteTreeById((Serializable) map.get(tableInfo.getKeyColumn()), pidColumn);
//            }
//        }
//
//        this.deleteById(id);
//    }


}
