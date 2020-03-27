package test;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONArray;
import com.google.common.io.CharStreams;
import listen.TdateTypeListen;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import model.TwoTType;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
public class ExcelDec {

    // 测试读文件流
    @Test
    public void t1() {
        try {
            // 根据resource文件路径，生成文件
            ClassPathResource resource = new ClassPathResource("/twoTimeType.xlsx");
            // 解析文件为指定编码的字符串
            String string = CharStreams.toString(new InputStreamReader(resource.getInputStream(), "UTF-8"));
            log.info(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 两种时间类型转换
     * ymd
     * ymd hms
     * 注意
     * @SneakyThrows 可排除编译器的异常处理
     *
     */
    @Test
    @SneakyThrows
    public void t2() {
            // 根据resource文件路径，生成文件
            ClassPathResource resource = new ClassPathResource("/twoTimeType.xlsx");
            EasyExcel.read(resource.getInputStream(),new TdateTypeListen()).head(TwoTType.class).sheet().doRead();
    }

    @Test
    public void t3() {

    }

    @Test
    public void t4() {

    }

    @Test
    public void t5() {

    }

    @Test
    public void t6() {

    }


    @Test
    public void t7() {

    }


    @Test
    public void t8() {

    }


}
