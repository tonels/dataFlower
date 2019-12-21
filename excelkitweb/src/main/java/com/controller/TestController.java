package com.controller;

import com.commonData.Student;
import com.commonData.User;
import com.google.common.collect.Lists;
import com.util.CollectionUtil;
import com.util.DbUtil;
import com.util.MapUtil;
import com.wuwenze.poi.ExcelKit;
import com.wuwenze.poi.handler.ExcelReadHandler;
import com.wuwenze.poi.pojo.ExcelErrorField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/")
public class TestController {

    @GetMapping("/index")
    public String index(){
        return "INDEX";
    }

    /**
     * 已测试，成功导出模板
     * 使用 ExcelKit 提供的API 构建导入模板, 会根据配置生成批注, 下拉框等
     * 生成导入模板（含3条示例数据）
     * @param response
     */
    @RequestMapping(value = "/downTemplate", method = RequestMethod.GET)
    public void downTemplate(HttpServletResponse response) {
        List<Student> students = DbUtil.getStudentList(3);
        ExcelKit.$Export(Student.class, response).downXlsx(students, true);
    }

    /**
     * todo 待处理，没测成功
     *  执行文件导入
     *  使用边读边处理的方式, 无需担心内存溢出, 也不用理会 Excel 文件到底有多大.
     *
     * 可测试全部成功和部分成功的
     */
    @RequestMapping(value = "/importUser", method = RequestMethod.POST)
    public ResponseEntity<?> importUser(@RequestParam MultipartFile file) throws IOException {
        long beginMillis = System.currentTimeMillis();

        List<Student> successList = Lists.newArrayList();
        List<Map<String, Object>> errorList = Lists.newArrayList();

        ExcelKit.$Import(Student.class)
                .readXlsx(file.getInputStream(), new ExcelReadHandler<Student>() {

                    @Override
                    public void onSuccess(int sheetIndex, int rowIndex, Student entity) {
                        successList.add(entity); // 单行读取成功，加入入库队列。
                    }

                    @Override
                    public void onError(int sheetIndex, int rowIndex,
                                        List<ExcelErrorField> errorFields) {
                        // 读取数据失败，记录了当前行所有失败的数据
                        errorList.add(MapUtil.newHashMap(//
                                "sheetIndex", sheetIndex,//
                                "rowIndex", rowIndex,//
                                "errorFields", errorFields//
                        ));
                    }
                });

        // TODO: 执行successList的入库操作。

        return ResponseEntity.ok(MapUtil.newHashMap(
                "data", successList,
                "haveError", !CollectionUtil.isEmpty(errorList),
                "error", errorList,
                "timeConsuming", (System.currentTimeMillis() - beginMillis) / 1000L
        ));
    }


    /**
     *  一行代码执行 Excel 批量导出.
     * 基于 Apache POI SXSSF 系列API实现导出, 大幅优化导出性能，也要量力而行
     * 已测，成功
     */
    @RequestMapping(value = "/downXlsx", method = RequestMethod.GET)
    public void downXlsx(HttpServletResponse response) {
        long beginMillis = System.currentTimeMillis();
        List<Student> stuList = DbUtil.getStudentList(100);// 生成10w条测试数据
        ExcelKit.$Export(Student.class, response).downXlsx(stuList, false);
        log.info("#ExcelKit.$Export success, size={},timeConsuming={}s",//
                stuList.size(), (System.currentTimeMillis() - beginMillis) / 1000L);
    }


}
