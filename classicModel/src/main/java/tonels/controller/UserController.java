package tonels.controller;

import com.alibaba.excel.EasyExcel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tonels.excel.UserExport;
import tonels.listen.M1Listen;
import tonels.mapper.CustomerMapper;
import tonels.model.DbExcel1;
import tonels.model.UserInfo;
import tonels.repository.UserRepository;
import tonels.util.ResultBean;
import tonels.util.TestFileUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("excel")
public class UserController {

    @Resource
    private UserRepository userRepository;
    @Resource
    private CustomerMapper customerMapper;

    /**
     * 浏览器访问，模板导出
     * todo 无数据是导出，会报错
     *
     * @return
     */
    @GetMapping("/template-export")
    public ResultBean templateExport(HttpServletResponse response) throws IOException, Exception {

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("测试", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), UserExport.class).sheet("模板-无数据").doWrite(null);
        return ResultBean.ok();
    }

    /**
     * 导出
     *
     * @return
     */
    @GetMapping("/export1")
    public void export1(HttpServletResponse response) throws IOException, Exception {
        List<UserExport> collect = userRepository.findAll()
                .stream()
                .map(customerMapper::userToExport)
                .collect(Collectors.toList());

        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("测试", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), UserExport.class).sheet("模板-数据").doWrite(collect);
    }

//    @GetMapping("/sheetByPage")
//    public void repeatedWrite2(HttpServletResponse response) throws IOException {
//        // 方法2 如果写到不同的sheet 同一个对象
//        String fileName = TestFileUtil.getPath() + "repeatedWrite" + System.currentTimeMillis() + ".xlsx";
//        // 这里 指定文件
//        response.setContentType("application/vnd.ms-excel");
//        response.setCharacterEncoding("utf-8");
//        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
//
//        Pageable of = PageRequest.of(0, 2);
//        Page<UserInfo> all = userRepository.findAll(of);
//        int totalPages = all.getTotalPages();
//
//        if (!all.isLast()) {
//            all.
//        }
//
//        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream(), UserExport.class).build();
//        // 去调用写入,这里我调用了五次，实际使用时根据数据库分页的总的页数来。这里最终会写到5个sheet里面
//        for (int i = 0; i < 5; i++) {
//            // 每次都要创建writeSheet 这里注意必须指定sheetNo
//            WriteSheet writeSheet = EasyExcel.writerSheet(i, "方法2模板"+(i+1)).build();
//            // 分页去数据库查询数据 这里可以去数据库查询每一页的数据
//
//            List<UserExport> data = data();
//            excelWriter.write(data, writeSheet);
//        }
//        /// 千万别忘记finish 会帮忙关闭流
//        excelWriter.finish();
//    }

    @GetMapping("/page")
    public void t2() {
        Page<UserInfo> all = userRepository.findAll(PageRequest.of(0, 5));

        for (int i = 0; i < all.getTotalPages(); i++) {
            Page<UserInfo> a = userRepository.findAll(PageRequest.of(0, 5));

            List<UserInfo> b = all.getContent();
            b.stream().map(UserInfo::getUserId).forEach(System.out::println);

        }

    }


    /**
     * 基于模板导出
     */
    @GetMapping("/export-with-template")
    public ResultBean exportWithTemplate() {
        return null;
    }

    /**
     * Read1 -- 导入
     */
    @GetMapping("/import1")
    public ResultBean import1(/*@RequestParam(value = "file") MultipartFile file*/) throws IOException {
//        EasyExcel.read(file.getInputStream(), new M1Listen()).sheet("导出DB设计文档").doRead();
//
        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "导出DB设计文档.xlsx";
        // 这里 需要指定读用哪个class去读，然后指定读取Sheet（默认会读第一个，这里注意原有的Excel的格式问题）
//        List<DbExcel1> sheet1 = EasyExcel.read(fileName, DbExcel1.class, new M1Listen()).sheet("Sheet1").doRead(); // 同步读
        List<DbExcel1> sheet1 = EasyExcel.read(fileName, DbExcel1.class, new M1Listen()).sheet("Sheet1").doReadSync();
        Map<String, List<DbExcel1>> collect = sheet1.stream().collect(Collectors.groupingBy(DbExcel1::getTableName));

        return ResultBean.ok(collect);
    }

    /**
     * Read2 -- 基于模板导入
     */
    @PostMapping("/import-with-template")
    public ResultBean importWithTemplate() {
        return null;
    }


}
