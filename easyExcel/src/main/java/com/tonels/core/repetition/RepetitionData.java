package com.tonels.core.repetition;

import com.alibaba.excel.annotation.ExcelProperty;

import lombok.Data;

/**
 * @author Jiaju Zhuang
 */
@Data
public class RepetitionData {
    @ExcelProperty("字符串")
    private String string;
}
