package com.commonData;

import com.wuwenze.poi.convert.WriteConverter;
import com.wuwenze.poi.exception.ExcelKitWriteConverterException;

public class CustomizeFieldWriteConverter implements WriteConverter {

    /**
     * 将value转换成指定的值, 用于写入excel表格中
     *
     * @param value 当前单元格的值
     * @return 转换后的值
     */
    @Override
    public String convert(Object value) throws ExcelKitWriteConverterException {
        return (value + "_convertedValue");
    }
}
