package net.tree_3.department;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sun.jndi.toolkit.dir.SearchFilter;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.annotation.Order;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 首先，这里的树，是基于父子同表的数据构建的，
 * todo 同表和异表，操作的不同
 * 这里涉及到，树的构建，层级删除，层级批量添加
 */

public class DepartmentTest {

    private List<Department> tree;
    private List<Department> list;

    /**
     * 初始化数据
     */
    @Before
    public void t1() {
        String jsonTree = "[\n" +
                "        {\n" +
                "            \"departmentId\": -1,\n" +
                "            \"departmentName\": \"上海网达\",\n" +
                "            \"level\": 0,\n" +
                "            \"parentId\": 0,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": [\n" +
                "                {\n" +
                "                    \"departmentId\": 1,\n" +
                "                    \"departmentName\": \"产品研发中心\",\n" +
                "                    \"level\": 1,\n" +
                "                    \"parentId\": -1,\n" +
                "                    \"createBy\": \"system\",\n" +
                "                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                    \"updateBy\": \"system\",\n" +
                "                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                    \"child\": [\n" +
                "                        {\n" +
                "                            \"departmentId\": 10,\n" +
                "                            \"departmentName\": \"UI设计部\",\n" +
                "                            \"level\": 2,\n" +
                "                            \"parentId\": 1,\n" +
                "                            \"createBy\": \"system\",\n" +
                "                            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                            \"updateBy\": \"system\",\n" +
                "                            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                            \"child\": null\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"departmentId\": 11,\n" +
                "                            \"departmentName\": \"杭州研发部\",\n" +
                "                            \"level\": 2,\n" +
                "                            \"parentId\": 1,\n" +
                "                            \"createBy\": \"system\",\n" +
                "                            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                            \"updateBy\": \"system\",\n" +
                "                            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                            \"child\": [\n" +
                "                                {\n" +
                "                                    \"departmentId\": 109,\n" +
                "                                    \"departmentName\": \"医疗产品开发组\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 11,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"departmentId\": 123,\n" +
                "                                    \"departmentName\": \"阅读产品开发组\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 11,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"departmentId\": 129,\n" +
                "                                    \"departmentName\": \"客户端开发组\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 11,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"departmentId\": 12,\n" +
                "                            \"departmentName\": \"合肥研发部\",\n" +
                "                            \"level\": 2,\n" +
                "                            \"parentId\": 1,\n" +
                "                            \"createBy\": \"system\",\n" +
                "                            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                            \"updateBy\": \"system\",\n" +
                "                            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                            \"child\": [\n" +
                "                                {\n" +
                "                                    \"departmentId\": 100,\n" +
                "                                    \"departmentName\": \"客户端组\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 12,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"departmentId\": 116,\n" +
                "                                    \"departmentName\": \"云剪开发组   \",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 12,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"departmentId\": 122,\n" +
                "                                    \"departmentName\": \"定制开发组\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 12,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"departmentId\": 130,\n" +
                "                                    \"departmentName\": \"消息开发组\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 12,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"departmentId\": 13,\n" +
                "                            \"departmentName\": \"集成运维部\",\n" +
                "                            \"level\": 2,\n" +
                "                            \"parentId\": 1,\n" +
                "                            \"createBy\": \"system\",\n" +
                "                            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                            \"updateBy\": \"system\",\n" +
                "                            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                            \"child\": [\n" +
                "                                {\n" +
                "                                    \"departmentId\": 101,\n" +
                "                                    \"departmentName\": \"直播运维组\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 13,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"departmentId\": 108,\n" +
                "                                    \"departmentName\": \"集成技术部\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 13,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"departmentId\": 111,\n" +
                "                                    \"departmentName\": \"咪咕运维部\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 13,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"departmentId\": 14,\n" +
                "                            \"departmentName\": \"上海测试部\",\n" +
                "                            \"level\": 2,\n" +
                "                            \"parentId\": 1,\n" +
                "                            \"createBy\": \"system\",\n" +
                "                            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                            \"updateBy\": \"system\",\n" +
                "                            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                            \"child\": [\n" +
                "                                {\n" +
                "                                    \"departmentId\": 110,\n" +
                "                                    \"departmentName\": \"WEB端测试部\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 14,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"departmentId\": 137,\n" +
                "                                    \"departmentName\": \"终端测试部\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 14,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"departmentId\": 15,\n" +
                "                            \"departmentName\": \"上海研发1部\",\n" +
                "                            \"level\": 2,\n" +
                "                            \"parentId\": 1,\n" +
                "                            \"createBy\": \"system\",\n" +
                "                            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                            \"updateBy\": \"system\",\n" +
                "                            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                            \"child\": [\n" +
                "                                {\n" +
                "                                    \"departmentId\": 105,\n" +
                "                                    \"departmentName\": \"直播产品IOS组\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 15,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"departmentId\": 106,\n" +
                "                                    \"departmentName\": \"大屏开发组\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 15,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"departmentId\": 114,\n" +
                "                                    \"departmentName\": \"创新小组\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 15,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"departmentId\": 117,\n" +
                "                                    \"departmentName\": \"影院产品IOS组\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 15,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"departmentId\": 128,\n" +
                "                                    \"departmentName\": \"直播产品Android组\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 15,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"departmentId\": 135,\n" +
                "                                    \"departmentName\": \"影院产品Android组\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 15,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"departmentId\": 136,\n" +
                "                                    \"departmentName\": \"视频产品IOS组\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 15,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"departmentId\": 139,\n" +
                "                                    \"departmentName\": \"视频产品Android组\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 15,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"departmentId\": 16,\n" +
                "                            \"departmentName\": \"上海研发2部\",\n" +
                "                            \"level\": 2,\n" +
                "                            \"parentId\": 1,\n" +
                "                            \"createBy\": \"system\",\n" +
                "                            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                            \"updateBy\": \"system\",\n" +
                "                            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                            \"child\": [\n" +
                "                                {\n" +
                "                                    \"departmentId\": 103,\n" +
                "                                    \"departmentName\": \"测试组\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 16,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"departmentId\": 119,\n" +
                "                                    \"departmentName\": \"前端开发组\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 16,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"departmentId\": 124,\n" +
                "                                    \"departmentName\": \"影院产品开发组\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 16,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"departmentId\": 133,\n" +
                "                                    \"departmentName\": \"视频产品开发组\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 16,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"departmentId\": 138,\n" +
                "                                    \"departmentName\": \"后端能力开发组\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 16,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"departmentId\": 17,\n" +
                "                            \"departmentName\": \"上海研发3部\",\n" +
                "                            \"level\": 2,\n" +
                "                            \"parentId\": 1,\n" +
                "                            \"createBy\": \"system\",\n" +
                "                            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                            \"updateBy\": \"system\",\n" +
                "                            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                            \"child\": [\n" +
                "                                {\n" +
                "                                    \"departmentId\": 112,\n" +
                "                                    \"departmentName\": \"体育赛事开发组\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 17,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"departmentId\": 113,\n" +
                "                                    \"departmentName\": \"视频生产开发组\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 17,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"departmentId\": 115,\n" +
                "                                    \"departmentName\": \"视频中台开发组\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 17,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"departmentId\": 118,\n" +
                "                                    \"departmentName\": \"视频云开发组\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 17,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"departmentId\": 120,\n" +
                "                                    \"departmentName\": \"直播运营开发组\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 17,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"departmentId\": 121,\n" +
                "                                    \"departmentName\": \"应用商店开发组\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 17,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"departmentId\": 125,\n" +
                "                                    \"departmentName\": \"内容分发开发组\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 17,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"departmentId\": 126,\n" +
                "                                    \"departmentName\": \"前端技术组\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 17,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"departmentId\": 127,\n" +
                "                                    \"departmentName\": \"内容审核开发组\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 17,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"departmentId\": 131,\n" +
                "                                    \"departmentName\": \"产品运营开发组\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 17,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"departmentId\": 132,\n" +
                "                                    \"departmentName\": \"多屏互动开发组\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 17,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"departmentId\": 134,\n" +
                "                                    \"departmentName\": \"分省产品开发组\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 17,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"departmentId\": 141,\n" +
                "                                    \"departmentName\": \"人工智能组\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 17,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"departmentId\": 142,\n" +
                "                                    \"departmentName\": \"大屏产品开发组\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 17,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"departmentId\": 18,\n" +
                "                            \"departmentName\": \"项目管理部\",\n" +
                "                            \"level\": 2,\n" +
                "                            \"parentId\": 1,\n" +
                "                            \"createBy\": \"system\",\n" +
                "                            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                            \"updateBy\": \"system\",\n" +
                "                            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                            \"child\": [\n" +
                "                                {\n" +
                "                                    \"departmentId\": 104,\n" +
                "                                    \"departmentName\": \"项目审计组\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 18,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"departmentId\": 107,\n" +
                "                                    \"departmentName\": \"过程管理组\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 18,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"updateBy\": \"system\",\n" +
                "                                    \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "                                    \"child\": null\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"departmentId\": 140,\n" +
                "                                    \"departmentName\": \"项目管理组\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 18,\n" +
                "                                    \"createBy\": \"system\",\n" +
                "                                    \"createTime\": \"2019-12-26 08:52:05\",\n" +
                "                                    \"updateBy\": \"admin\",\n" +
                "                                    \"updateTime\": \"2019-12-26 08:52:05\",\n" +
                "                                    \"child\": null\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"departmentId\": 202,\n" +
                "                    \"departmentName\": \"Zty功能测试中心\",\n" +
                "                    \"level\": 1,\n" +
                "                    \"parentId\": -1,\n" +
                "                    \"createBy\": \"admin\",\n" +
                "                    \"createTime\": \"2019-12-26 11:22:34\",\n" +
                "                    \"updateBy\": \"admin\",\n" +
                "                    \"updateTime\": \"2019-12-26 11:22:34\",\n" +
                "                    \"child\": null\n" +
                "                },\n" +
                "                {\n" +
                "                    \"departmentId\": 254,\n" +
                "                    \"departmentName\": \"ZT测试部门\",\n" +
                "                    \"level\": 1,\n" +
                "                    \"parentId\": -1,\n" +
                "                    \"createBy\": \"z0001\",\n" +
                "                    \"createTime\": \"2019-12-27 10:39:04\",\n" +
                "                    \"updateBy\": \"z0001\",\n" +
                "                    \"updateTime\": \"2019-12-27 10:39:04\",\n" +
                "                    \"child\": [\n" +
                "                        {\n" +
                "                            \"departmentId\": 256,\n" +
                "                            \"departmentName\": \"ZT-1测试部门\",\n" +
                "                            \"level\": 2,\n" +
                "                            \"parentId\": 254,\n" +
                "                            \"createBy\": \"z0001\",\n" +
                "                            \"createTime\": \"2019-12-27 10:43:38\",\n" +
                "                            \"updateBy\": \"z0001\",\n" +
                "                            \"updateTime\": \"2019-12-27 10:43:38\",\n" +
                "                            \"child\": [\n" +
                "                                {\n" +
                "                                    \"departmentId\": 257,\n" +
                "                                    \"departmentName\": \"ZT-1-1测试部门\",\n" +
                "                                    \"level\": 3,\n" +
                "                                    \"parentId\": 256,\n" +
                "                                    \"createBy\": \"z0001\",\n" +
                "                                    \"createTime\": \"2019-12-27 10:44:12\",\n" +
                "                                    \"updateBy\": \"z0001\",\n" +
                "                                    \"updateTime\": \"2019-12-27 10:44:12\",\n" +
                "                                    \"child\": null\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"departmentId\": 255,\n" +
                "                    \"departmentName\": \"ZY测试部门\",\n" +
                "                    \"level\": 1,\n" +
                "                    \"parentId\": -1,\n" +
                "                    \"createBy\": \"z0001\",\n" +
                "                    \"createTime\": \"2019-12-27 10:43:11\",\n" +
                "                    \"updateBy\": \"z0001\",\n" +
                "                    \"updateTime\": \"2019-12-27 10:43:11\",\n" +
                "                    \"child\": [\n" +
                "                        {\n" +
                "                            \"departmentId\": 258,\n" +
                "                            \"departmentName\": \"ZY-1测试部门\",\n" +
                "                            \"level\": 2,\n" +
                "                            \"parentId\": 255,\n" +
                "                            \"createBy\": \"z0001\",\n" +
                "                            \"createTime\": \"2019-12-27 10:45:01\",\n" +
                "                            \"updateBy\": \"z0001\",\n" +
                "                            \"updateTime\": \"2019-12-27 10:45:01\",\n" +
                "                            \"child\": null\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"departmentId\": 259,\n" +
                "                    \"departmentName\": \"测试的部门\",\n" +
                "                    \"level\": 1,\n" +
                "                    \"parentId\": -1,\n" +
                "                    \"createBy\": \"z0002\",\n" +
                "                    \"createTime\": \"2019-12-27 11:14:29\",\n" +
                "                    \"updateBy\": \"z0002\",\n" +
                "                    \"updateTime\": \"2019-12-27 11:14:29\",\n" +
                "                    \"child\": null\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]";

        String jsonList = "[\n" +
                "        {\n" +
                "            \"departmentId\": 1,\n" +
                "            \"departmentName\": \"产品研发中心\",\n" +
                "            \"level\": 1,\n" +
                "            \"parentId\": -1,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 10,\n" +
                "            \"departmentName\": \"UI设计部\",\n" +
                "            \"level\": 2,\n" +
                "            \"parentId\": 1,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 11,\n" +
                "            \"departmentName\": \"杭州研发部\",\n" +
                "            \"level\": 2,\n" +
                "            \"parentId\": 1,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 12,\n" +
                "            \"departmentName\": \"合肥研发部\",\n" +
                "            \"level\": 2,\n" +
                "            \"parentId\": 1,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 13,\n" +
                "            \"departmentName\": \"集成运维部\",\n" +
                "            \"level\": 2,\n" +
                "            \"parentId\": 1,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 14,\n" +
                "            \"departmentName\": \"上海测试部\",\n" +
                "            \"level\": 2,\n" +
                "            \"parentId\": 1,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 15,\n" +
                "            \"departmentName\": \"上海研发1部\",\n" +
                "            \"level\": 2,\n" +
                "            \"parentId\": 1,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 16,\n" +
                "            \"departmentName\": \"上海研发2部\",\n" +
                "            \"level\": 2,\n" +
                "            \"parentId\": 1,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 17,\n" +
                "            \"departmentName\": \"上海研发3部\",\n" +
                "            \"level\": 2,\n" +
                "            \"parentId\": 1,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 18,\n" +
                "            \"departmentName\": \"项目管理部\",\n" +
                "            \"level\": 2,\n" +
                "            \"parentId\": 1,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 100,\n" +
                "            \"departmentName\": \"客户端组\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 12,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 101,\n" +
                "            \"departmentName\": \"直播运维组\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 13,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 103,\n" +
                "            \"departmentName\": \"测试组\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 16,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 104,\n" +
                "            \"departmentName\": \"项目审计组\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 18,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 105,\n" +
                "            \"departmentName\": \"直播产品IOS组\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 15,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 106,\n" +
                "            \"departmentName\": \"大屏开发组\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 15,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 107,\n" +
                "            \"departmentName\": \"过程管理组\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 18,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 108,\n" +
                "            \"departmentName\": \"集成技术部\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 13,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 109,\n" +
                "            \"departmentName\": \"医疗产品开发组\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 11,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 110,\n" +
                "            \"departmentName\": \"WEB端测试部\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 14,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 111,\n" +
                "            \"departmentName\": \"咪咕运维部\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 13,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 112,\n" +
                "            \"departmentName\": \"体育赛事开发组\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 17,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 113,\n" +
                "            \"departmentName\": \"视频生产开发组\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 17,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 114,\n" +
                "            \"departmentName\": \"创新小组\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 15,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 115,\n" +
                "            \"departmentName\": \"视频中台开发组\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 17,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 116,\n" +
                "            \"departmentName\": \"云剪开发组   \",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 12,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 117,\n" +
                "            \"departmentName\": \"影院产品IOS组\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 15,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 118,\n" +
                "            \"departmentName\": \"视频云开发组\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 17,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 119,\n" +
                "            \"departmentName\": \"前端开发组\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 16,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 120,\n" +
                "            \"departmentName\": \"直播运营开发组\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 17,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 121,\n" +
                "            \"departmentName\": \"应用商店开发组\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 17,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 122,\n" +
                "            \"departmentName\": \"定制开发组\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 12,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 123,\n" +
                "            \"departmentName\": \"阅读产品开发组\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 11,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 124,\n" +
                "            \"departmentName\": \"影院产品开发组\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 16,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 125,\n" +
                "            \"departmentName\": \"内容分发开发组\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 17,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 126,\n" +
                "            \"departmentName\": \"前端技术组\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 17,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 127,\n" +
                "            \"departmentName\": \"内容审核开发组\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 17,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 128,\n" +
                "            \"departmentName\": \"直播产品Android组\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 15,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 129,\n" +
                "            \"departmentName\": \"客户端开发组\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 11,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 130,\n" +
                "            \"departmentName\": \"消息开发组\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 12,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 131,\n" +
                "            \"departmentName\": \"产品运营开发组\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 17,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 132,\n" +
                "            \"departmentName\": \"多屏互动开发组\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 17,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 133,\n" +
                "            \"departmentName\": \"视频产品开发组\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 16,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 134,\n" +
                "            \"departmentName\": \"分省产品开发组\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 17,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 135,\n" +
                "            \"departmentName\": \"影院产品Android组\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 15,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 136,\n" +
                "            \"departmentName\": \"视频产品IOS组\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 15,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 137,\n" +
                "            \"departmentName\": \"终端测试部\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 14,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 138,\n" +
                "            \"departmentName\": \"后端能力开发组\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 16,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 139,\n" +
                "            \"departmentName\": \"视频产品Android组\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 15,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 140,\n" +
                "            \"departmentName\": \"项目管理组\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 18,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-26 08:52:05\",\n" +
                "            \"updateBy\": \"admin\",\n" +
                "            \"updateTime\": \"2019-12-26 08:52:05\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 141,\n" +
                "            \"departmentName\": \"人工智能组\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 17,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 142,\n" +
                "            \"departmentName\": \"大屏产品开发组\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 17,\n" +
                "            \"createBy\": \"system\",\n" +
                "            \"createTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"updateBy\": \"system\",\n" +
                "            \"updateTime\": \"2019-12-25 14:33:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 202,\n" +
                "            \"departmentName\": \"Zty功能测试中心\",\n" +
                "            \"level\": 1,\n" +
                "            \"parentId\": -1,\n" +
                "            \"createBy\": \"admin\",\n" +
                "            \"createTime\": \"2019-12-26 11:22:34\",\n" +
                "            \"updateBy\": \"admin\",\n" +
                "            \"updateTime\": \"2019-12-26 11:22:34\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 254,\n" +
                "            \"departmentName\": \"ZT测试部门\",\n" +
                "            \"level\": 1,\n" +
                "            \"parentId\": -1,\n" +
                "            \"createBy\": \"z0001\",\n" +
                "            \"createTime\": \"2019-12-27 10:39:04\",\n" +
                "            \"updateBy\": \"z0001\",\n" +
                "            \"updateTime\": \"2019-12-27 10:39:04\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 255,\n" +
                "            \"departmentName\": \"ZY测试部门\",\n" +
                "            \"level\": 1,\n" +
                "            \"parentId\": -1,\n" +
                "            \"createBy\": \"z0001\",\n" +
                "            \"createTime\": \"2019-12-27 10:43:11\",\n" +
                "            \"updateBy\": \"z0001\",\n" +
                "            \"updateTime\": \"2019-12-27 10:43:11\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 256,\n" +
                "            \"departmentName\": \"ZT-1测试部门\",\n" +
                "            \"level\": 2,\n" +
                "            \"parentId\": 254,\n" +
                "            \"createBy\": \"z0001\",\n" +
                "            \"createTime\": \"2019-12-27 10:43:38\",\n" +
                "            \"updateBy\": \"z0001\",\n" +
                "            \"updateTime\": \"2019-12-27 10:43:38\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 257,\n" +
                "            \"departmentName\": \"ZT-1-1测试部门\",\n" +
                "            \"level\": 3,\n" +
                "            \"parentId\": 256,\n" +
                "            \"createBy\": \"z0001\",\n" +
                "            \"createTime\": \"2019-12-27 10:44:12\",\n" +
                "            \"updateBy\": \"z0001\",\n" +
                "            \"updateTime\": \"2019-12-27 10:44:12\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 258,\n" +
                "            \"departmentName\": \"ZY-1测试部门\",\n" +
                "            \"level\": 2,\n" +
                "            \"parentId\": 255,\n" +
                "            \"createBy\": \"z0001\",\n" +
                "            \"createTime\": \"2019-12-27 10:45:01\",\n" +
                "            \"updateBy\": \"z0001\",\n" +
                "            \"updateTime\": \"2019-12-27 10:45:01\",\n" +
                "            \"child\": null\n" +
                "        },\n" +
                "        {\n" +
                "            \"departmentId\": 259,\n" +
                "            \"departmentName\": \"测试的部门\",\n" +
                "            \"level\": 1,\n" +
                "            \"parentId\": -1,\n" +
                "            \"createBy\": \"z0002\",\n" +
                "            \"createTime\": \"2019-12-27 11:14:29\",\n" +
                "            \"updateBy\": \"z0002\",\n" +
                "            \"updateTime\": \"2019-12-27 11:14:29\",\n" +
                "            \"child\": null\n" +
                "        }\n" +
                "    ]";

        tree = JSONArray.parseArray(jsonTree, Department.class);
        list = JSONArray.parseArray(jsonList, Department.class);


    }

    @Test
    public void t2() {
        Department tree1 = tree.get(0);
        Department list1 = list.get(0);
    }

    // ==========================基于列表,构建树=======================================
    @Test
    public void t3() {
        Map<Long, Department> nodes = new LinkedHashMap<>();
        for (Department department : list) {
            nodes.put(department.getDepartmentId(), department);
        }
        getNodeJson(0L, nodes);
    }

    public List<Department> getNodeJson(Long departmentId, Map<Long, Department> nodes) {
        //获取当前节点下面的所有子节点
        List<Department> childList = getChildNodes(departmentId, nodes);
        List<Department> childTree = new ArrayList<>();
        for (Department node : childList) {
            Department depart = new Department();
            depart.setDepartmentId(node.getDepartmentId());
            depart.setDepartmentName(node.getDepartmentName());
            depart.setLevel(node.getLevel());
            depart.setParentId(node.getParentId());
            depart.setCreateBy(node.getCreateBy());
            depart.setCreateTime(node.getCreateTime());
            depart.setUpdateBy(node.getUpdateBy());
            depart.setUpdateTime(node.getUpdateTime());
            List<Department> childs = getNodeJson(node.getDepartmentId(), nodes); //递归获取当前部门下面的所有子部门
            if (!childs.isEmpty()) {
                depart.setChild(childs);
            }
            childTree.add(depart);
        }
        return childTree;
    }

    public List<Department> getChildNodes(Long departmentId, Map<Long, Department> nodes) {
        List<Department> list = new ArrayList<>();
        for (Long key : nodes.keySet()) {
            if (nodes.get(key).getParentId().longValue() == departmentId.longValue()) {
                list.add(nodes.get(key));
            }
        }
        return list;
    }

    // ==========================基于列表,构建树=======================================
// ===================指定根节点，向下递归遍历取得所有的节点ID=======================================
    @Test
    public void findByDepartmentId() {
        Map<Long, Department> nodes = new LinkedHashMap<>();
        for (Department department : list) {
            nodes.put(department.getDepartmentId(), department);
        }
        List<Department> departmentList = getNodeJson(-1L, nodes);
        List<Department> list = new ArrayList<>();

        it(departmentList, list);

        List<Long> ids = new ArrayList<>();
        for (Department department : list) {
            ids.add(department.getDepartmentId());
        }
        System.out.println(ids.size());
    }

    public void it(List<Department> departments, List<Department> list) {
        departments.forEach(dm -> {
            List<Department> child = dm.getChild();
            dm.setChild(null);
            list.add(dm);
            if (null != child) {
                it(child, list);
            }
        });
    }
// =============================基于根节点，向下递归遍历取得所有的节点ID=============================


// ==========================================================


// ============================= 基于节点，构建树结构 =============================
    // =======  基于所有节点，构建树结构 =======================
    @Test
    public void findTypeTree() {
        List<Object> des = new ArrayList<>();
        for (Department depart : list) {
            JSONObject treeObject = new JSONObject(true);
            treeObject.put("id", depart.getDepartmentId());
            treeObject.put("pid", depart.getParentId());
            treeObject.put("name", depart.getDepartmentName());
            treeObject.put("children", getChildren(depart.getDepartmentId()));
            des.add(treeObject);
        }
    }

    // =======  基于单个节点，构建树结构 =======================
    @Test
    public void findTreeById(){
        List<Object> children = this.getChildren(-1L);
    }

    public List<Object> getChildren(Long departmnetId){
        List<Object> objectList = new ArrayList<>();
        List<Department> children = list.stream().filter(e -> e.getParentId().equals(departmnetId)).collect(Collectors.toList());
        for (Department department : children) {
            JSONObject obj = new JSONObject(true);
            obj.put("id", department.getDepartmentId());
            obj.put("pid", department.getParentId());
            obj.put("name", department.getDepartmentName());
            obj.put("children", getChildren(department.getDepartmentId()));
            objectList.add(obj);
        }
        return objectList;
    }
// ============================= 基于节点，构建树结构 =============================

// =============================更简单实现 基于节点，构建树结构 =============================

    @Test
    public void t6(){
        List<Department> departments = this.buildMenuTree(list, -1L);
    }

    private List<Department> buildMenuTree(List<Department> departments, Long pid) {
        List<Department> treeList = new ArrayList<>();
        departments.forEach(department -> {
            if (department.getParentId() == pid) {
                department.setChild(buildMenuTree(departments, department.getDepartmentId()));
                treeList.add(department);
            }
        });
        return treeList;
    }
// =============================更简单实现 基于节点，构建树结构 =============================




}
