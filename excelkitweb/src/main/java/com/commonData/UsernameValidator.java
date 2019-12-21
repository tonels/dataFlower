package com.commonData;

import com.google.common.collect.Lists;
import com.wuwenze.poi.validator.Validator;

import java.util.List;

public class UsernameValidator implements Validator {
    final List<String> ERROR_USERNAME_LIST = Lists.newArrayList(
            "admin", "root", "master", "administrator", "sb"
    );

    @Override
    public String valid(Object o) {
        String username = (String) o;
        if (username.length() > 12) {
            return "用户名不能超过12个字符。";
        }

        if (ERROR_USERNAME_LIST.contains(username)) {
            return "用户名非法，不允许使用。";
        }
        return null;
    }
}
