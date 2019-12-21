package com.commonData;

import com.wuwenze.poi.convert.ReadConverter;
import com.wuwenze.poi.exception.ExcelKitReadConverterException;

public class CustomizeFieldReadConverter implements ReadConverter {

    /**
     * 将value转换成指定的值, 读取时映射到实体中
     *
     * @param o 当前单元格的值
     * @return 转换后的值
     */
    @Override
    public Object convert(Object o) throws ExcelKitReadConverterException {
        String value = (String) o;

        int convertedValue = 0;
        for (char c : value.toCharArray()) {
            convertedValue += Integer.valueOf(c);
        }
        return convertedValue;
    }
}
