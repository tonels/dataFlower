package com.xwy.poi.controller;

import com.sun.deploy.net.HttpResponse;
import com.xwy.poi.entity.Reqbase;
import com.xwy.poi.service.IPoiService;
import com.xwy.poi.service.IPoiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.annotation.W3CDomHandler;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

/**
 * ClassName: PoiControlller
 * Function:  TODO
 * Date:      2020/2/10 0010 18:25
 * author     XieWenYing
 * version    V1.0
 */
@Api(value = "poi读取word文档接口")
@RestController(value = "/poi")
public class PoiControlller {

    @Autowired
    private IPoiService poiService;

    /**
     * 读取word文档
     * @param file
     * @return
     */
    @ApiOperation(value = "word读取 支持doc和docx")
    @PostMapping(value = "/readWord")
    public List<Reqbase> readWord(@RequestParam(value = "file", required = true) MultipartFile file) {
        try {
        String fileName = file.getOriginalFilename();//判断doc或者docx

            if (fileName.endsWith(".docx")) {
                Path tempFile = Files.createTempFile(UUID.randomUUID().toString(), ".docx");
                Files.write(tempFile, file.getBytes());
                List<Reqbase> reqbases = poiService.readWord07(tempFile.toFile());
                Files.deleteIfExists(tempFile);
                return reqbases;
            } else if (fileName.endsWith(".doc")) {
                Path tempFile = Files.createTempFile(UUID.randomUUID().toString(), ".doc");
                Files.write(tempFile, file.getBytes());
                List<Reqbase> reqbases = poiService.readWord03(tempFile.toFile());
                Files.deleteIfExists(tempFile);
                return reqbases;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value = "word写入 支持doc和docx")
    @GetMapping(value = "/readWord")
    public void writeWord(HttpServletResponse response) {


    }


}
