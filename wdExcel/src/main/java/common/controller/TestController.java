package common.controller;

import common.cache.DictUtils;
import common.log.MethodLog;
import common.model.ExportPojo;
import common.model.PcPrdInfoChargeConfigImportVo;
import common.utils.DbGenerator;
import common.utils.excel.ExportExcel;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.IOUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
@Slf4j
@Scope("prototype")
public class TestController {

    @Resource
    private DictUtils dictUtils;

    /**
     * 测试成功
     *
     * @param response
     */
    // 从静态资源文件中提供模板下载
    @GetMapping("/d")
    public void d2(HttpServletResponse response) {
        InputStream is = null;
        ServletOutputStream os = null;
        String fileName = "用户模板.xlsx";
        try {
            is = new ClassPathResource("/template/USER.xlsx").getInputStream();

            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.addHeader("charset", "utf-8");
            response.addHeader("Pragma", "no-cache");
            String encodeName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString());
            response.setHeader("Content-Disposition", "attachment; filename=\"" + encodeName + "\"; filename*=utf-8''" + encodeName);

            os = response.getOutputStream();
            IOUtils.copy(is, os);
            response.flushBuffer();
            log.info("=============下载成功");
        } catch (Exception e) {
            log.error("=============下载失败 , fileName = " + fileName);
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
                log.error("=============下载失败l , fileName = " + fileName);
                e.printStackTrace();
            }
        }
    }

//    @ResponseBody
//    @PostMapping(value = "/import")
//    @MethodLog(module = "商品包-基本计费-导入",insertDB = true, opterate = 6,recordParam = false)
//    public ResultBean chargeImport( MultipartFile file ){
//        StringBuffer msg = new StringBuffer("导入数据总数:");
//        StringBuffer errMessage = new StringBuffer(";错误原因是:");
//        AtomicInteger errNum = new AtomicInteger();// 全局错误数
//        AtomicInteger successNum = new AtomicInteger();//成功导入数据数
//        AtomicInteger row=new AtomicInteger();//第几行
//        try {
//            ImportExcel ei = new ImportExcel(file, 0, 0);
////            List<PcPrdInfoChargeConfigImportVo> dataList = ei.getDataList(dictUtils,PcPrdInfoChargeConfigImportVo.class);
//            ei.getDataList(dictUtils,PcPrdInfoChargeConfigImportVo.class);
//            // 数据校验
////            msg.append(errNum);
////            msg.append(errMessage);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if(errNum.intValue()>0){
//            return ResultBean.error(msg.toString());
//        }else{
//            return ResultBean.ok(msg.toString());
//        }
//    }

    @ResponseBody
    @PostMapping(value = "/export")
    @MethodLog(module = "商品包-基本计费-导出",insertDB = true, opterate = 7,recordParam = false)
    public void prdInfoChargeConfigExport(HttpServletResponse response){

        List<ExportPojo> list = DbGenerator.getExportData();
        String fileName = "导出-商品包基本计费.xlsx";

//      excel标题
//		String[] title = {"商品包ID","计费类型","计费ID","权益节点","渠道包ID","码率收费","下载收费"};
        try {
            new ExportExcel(null, ExportPojo.class).setDataList(null,list).write(response, fileName).dispose();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/template/download")
    @MethodLog(module = "商品包-基本计费-模板下载",insertDB = true, opterate = 8,recordParam = false)
    public void chargeTemplateDownload(HttpServletResponse response){
        String fileName = "基本计费模板.xlsx";
        List<PcPrdInfoChargeConfigImportVo> list =new ArrayList<>();
        try {
            new ExportExcel(null, PcPrdInfoChargeConfigImportVo.class).setDataList(list).write(response, fileName).dispose();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }







}
