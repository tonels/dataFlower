package com.commonData;

import com.wuwenze.poi.validator.Validator;

public class UserEmailValidator implements Validator {
    /**
     * 验证单元格的值, 若验证失败, 请返回错误消息.
     *
     * @param value 当前单元格的值
     * @return null or errorMessage
     */
    @Override
    public String valid(Object value) {
        return null;
    }
}
