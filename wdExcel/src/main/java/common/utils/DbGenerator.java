package common.utils;

import com.google.common.collect.Lists;
import common.model.ExportPojo;

import java.util.ArrayList;
import java.util.List;

public class DbGenerator {

    // 生成10条测试数据
    public static List<ExportPojo> getExportData() {
        ArrayList<ExportPojo> list = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            ExportPojo pojo = new ExportPojo();
            pojo.setPrdInfoId(Long.valueOf(i)).setChargeType(i).setPrdInfoName("name"+i);
            list.add(pojo);
        }
        return list;
    }





}
