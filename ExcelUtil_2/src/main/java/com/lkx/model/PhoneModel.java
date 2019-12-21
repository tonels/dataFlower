package com.lkx.model;

import com.lkx.util.Excel;
import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

/**
 * ClassName:PhoneModel
 * Function: TODO ADD FUNCTION.
 * Reason: TODO ADD REASON.
 * Date:     2017年6月7日 上午9:43:19
 *
 * @author likaixuan
 * @version V1.0
 * @see
 * @since JDK 1.7
 */
@Data
public class PhoneModel implements Serializable {

    @Excel(title = "手机名称")
    private String phoneName;

    @Excel(title = "颜色")
    private String color;

    @Excel(title = "售价")
    private double price;

    private Date sj;

    @Override
    public String toString() {
        return "phoneName:" + phoneName + ",color:" + color + ",price:" + price + ",sj:" + sj;
    }

    /**
     * 解析字段注解
     *
     * @param clazz
     */
    public static <T> void parseField(Class<T> clazz) throws Exception {

        Field field;
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
        }
        for (int i = 0; i < fields.length; i++) {
            field = clazz.getDeclaredField(fields[i].getName());
            Excel column = field.getAnnotation(Excel.class);
            if (column != null) {
                System.out.println(fields[i].getName() + ":" + column.title());
            }
        }
    }


}

