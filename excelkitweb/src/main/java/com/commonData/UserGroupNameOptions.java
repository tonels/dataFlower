package com.commonData;

import com.wuwenze.poi.config.Options;

public class UserGroupNameOptions implements Options {

    /**
     * 指定excel单元格的下拉框数据源, 用于规范生成Excel模板的数据校验
     *
     * @return ["option1", "option2"]
     */
    @Override
    public String[] get() {
        return new String[]{"管理组", "普通会员组", "游客"};
    }
}
