package com.xwy.poi.service.ServiceImpl;

import com.xwy.poi.entity.Attach;
import com.xwy.poi.entity.Reqbase;
import com.xwy.poi.service.IPoiService;
import com.xwy.poi.utils.XWPFUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.*;
import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service
public class PoiServiceImpl implements IPoiService {
    @Override
    public List<Reqbase> readWord07(File wordFile) {
        // TempFileUtils.checkTempFiles();
        HashMap<String, Integer> counterMap = new HashMap<>();
        counterMap.put("curTable", 0);
        counterMap.put("curPara", 0);
        LinkedList<Reqbase> reqbases = new LinkedList<>();//存放解析出来的需求列表
        Reqbase reqbase = new Reqbase();
        reqbase.setOutline(-1);
        reqbase.setTitle("根节点");
        reqbases.addFirst(reqbase);

        XWPFDocument document = null;
        try {
            document = new XWPFDocument(Files.newInputStream(wordFile.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<IBodyElement> bodyElements = document.getBodyElements();
        //BodyElementType CONTENTCONTROL文本控件  PARAGRAPH段落  TABLE表格
        for (IBodyElement bodyElement : bodyElements) {
            handlerAdapterByIBodyElement(document, bodyElement, reqbases, counterMap);
        }

        return reqbases;
    }

    /**
     * 表格和文件图片暂时还没有定位到具体的段落中
     *
     * @param wordFile
     * @return
     */
    @Override
    public List<Reqbase> readWord03(File wordFile) {

        HWPFDocument doc = null;
        try {
            doc = new HWPFDocument(Files.newInputStream(wordFile.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        LinkedList<Reqbase> reqbases = new LinkedList<>();//存放解析出来的需求列表
        Reqbase reqbase = new Reqbase();
        reqbase.setOutline(-1);
        reqbase.setTitle("根节点");
        reqbases.addFirst(reqbase);


        Range range = doc.getRange();
        TableIterator tableIterator = new TableIterator(range);
        PicturesTable picturesTable = doc.getPicturesTable();

        int numParagraphs = range.numParagraphs();

        //解析段落
        Paragraph para;
        for (int i = 0; i < numParagraphs; i++) {
            para = range.getParagraph(i);
            String text = para.text();
            //段落
            if (!para.isInList() && !para.isInTable() &&
                    !text.trim().isEmpty() && !para.isEmbeddedCellMark()) {//不是列表或者表格中的段落
                int lvl = para.getLvl();
                System.out.println(lvl);
                System.out.println(text);
                if (text.contains("EMBED")) {//包含嵌入文件的文字，去掉
                    text = text.substring(0, text.lastIndexOf("EMBED"));
                }
                if (lvl != 9) {//不是正文， 新建reqbase
                    Reqbase req = new Reqbase();
                    req.setTitle(text);
                    req.setOutline(lvl);
                    reqbases.add(req);
                } else {//正文
                    Reqbase req = reqbases.peekLast();
                    req.setContent(req.getContent() != null ? req.getContent() : "" + text);
                }
            }

            //表格
            if (para.isInTable() && i != 0 && !range.getParagraph(i - 1).isInTable()) {
                Table table = range.getTable(para);
                if (table != null) {
                    OutputStream os = null;
                    try {

                        //建文件
                        String fileName = UUID.randomUUID().toString() + ".docx";
                        if (Files.notExists(Paths.get("attaches"))) Files.createDirectory(Paths.get("attaches"));
                        Files.createFile(Paths.get("attaches", fileName));
                        //插表
                        XWPFDocument xwpfDocument = new XWPFDocument();
                        XWPFTable table2 = xwpfDocument.createTable();
                        //画表
                        int rows = table.numRows();
                        int cells = 0;
                        for (int r = 0; r < rows; r++) {
                            TableRow row = table.getRow(r);
                            XWPFTableRow row2 = table2.createRow();
                            cells = row.numCells();
                            for (int c = 0; c < cells; c++) {
                                TableCell cell = row.getCell(c);
                                XWPFTableCell cell2 = row2.createCell();
                                cell2.setText(cell.text());
                            }
                        }
                        xwpfDocument.setTable(0, table2);
                        //保存表格到文档
                        os = Files.newOutputStream(Paths.get("attaches", fileName));
                        xwpfDocument.write(os);
                        //保存附件到需求
                        Attach attach = new Attach();
                        attach.setAname("表格 ");
                        attach.setApath("attaches" + File.separator + fileName);
                        Reqbase req = reqbases.peekLast();
                        if (null == req.getAttachList()) req.setAttachList(new ArrayList<>());
                        req.getAttachList().add(attach);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (null != os) {
                            try {
                                os.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

            //图片
            int runs = para.numCharacterRuns();
            for (int r = 0; r < runs; r++) {
                Picture picture = picturesTable.extractPicture(para.getCharacterRun(r), true);
                //建文件
                if (picture != null) {
                    String fileName = UUID.randomUUID().toString();
                    System.out.println(picture.suggestFileExtension());
                    System.out.println(picture.suggestFullFileName());
                    System.out.println(picture.suggestPictureType());

                    String suffix = "." + picture.suggestFileExtension();
                    fileName = fileName + suffix;


                    //存文件
                    OutputStream os = null;
                    try {
                        if (Files.notExists(Paths.get("attaches"))) Files.createDirectory(Paths.get("attaches"));
                        Files.createFile(Paths.get("attaches", fileName));
                        os = Files.newOutputStream(Paths.get("attaches", fileName));
                        picture.writeImageContent(os);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (null != os) {
                            try {
                                os.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    //保存附件到需求
                    Attach attach = new Attach();
                    attach.setAname("图片 " + picture.suggestFileExtension());
                    attach.setApath("attaches" + File.separator + fileName);
                    Reqbase req = reqbases.peekLast();
                    if (null == req.getAttachList()) req.setAttachList(new ArrayList<>());
                    req.getAttachList().add(attach);
                }
            }
        }


        return reqbases;
    }


    /**
     * 根据文档主体类型进行处理
     * BodyElementType CONTENTCONTROL文本控件  PARAGRAPH段落  TABLE表格
     *
     * @param bodyElement
     * @param reqbases
     */
    private void handlerAdapterByIBodyElement(XWPFDocument document, IBodyElement bodyElement, LinkedList<Reqbase> reqbases, HashMap<String, Integer> counterMap) {
        BodyElementType elementType = bodyElement.getElementType();
        System.out.println(elementType);
        switch (elementType) {
            case TABLE:
                //表格
                tableHandler(bodyElement, reqbases, counterMap);
                break;
            case PARAGRAPH:
                //段落
                paragraphHandler(document, bodyElement, reqbases, counterMap);
                break;
            case CONTENTCONTROL:
                //文本控件

                break;
            default:
                break;
        }
    }

    /**
     * 表格处理器
     *
     * @param bodyElement
     * @param reqbases
     */
    private void tableHandler(IBodyElement bodyElement, LinkedList<Reqbase> reqbases, HashMap<String, Integer> counterMap) {
        OutputStream os = null;
        try {
            //建文件
            String fileName = UUID.randomUUID().toString() + ".docx";
            if (Files.notExists(Paths.get("attaches"))) Files.createDirectory(Paths.get("attaches"));
            Files.createFile(Paths.get("attaches", fileName));
            //插表
            XWPFDocument xwpfDocument = new XWPFDocument();
            List<XWPFTable> tables = bodyElement.getBody().getTables();
            Integer curTable = counterMap.get("curTable");
            XWPFTable table1 = tables.get(curTable);

            xwpfDocument.createTable();
            xwpfDocument.setTable(0, table1);

            //保存表格到文档
            os = Files.newOutputStream(Paths.get("attaches", fileName));
            xwpfDocument.write(os);
            //保存附件到需求
            Attach attach = new Attach();
            attach.setAname("表格 " + curTable);
            attach.setApath("attaches" + File.separator + fileName);
            Reqbase reqbase = reqbases.peekLast();
            if (null == reqbase.getAttachList()) reqbase.setAttachList(new ArrayList<>());
            reqbase.getAttachList().add(attach);
            //表格计数器加1
            counterMap.replace("curTable", ++curTable);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 段落处理器
     *
     * @param bodyElement
     * @param reqbases
     */
    private void paragraphHandler(XWPFDocument document, IBodyElement bodyElement, LinkedList<Reqbase> reqbases, HashMap<String, Integer> counterMap) {
        try {
            List<XWPFParagraph> paragraphs = bodyElement.getBody().getParagraphs();

            Integer curPara = counterMap.get("curPara");
            XWPFParagraph paragraph = paragraphs.get(curPara);
            List<String> imageBundleList = XWPFUtils.readAttachInParagraph(paragraph).get("img");
            List<String> objectBundleList = XWPFUtils.readAttachInParagraph(paragraph).get("object");
            BigInteger paraOutlineLvl = XWPFUtils.getParaOutlineLvl(document, paragraph);
            //解析段落
            if (paraOutlineLvl != null) {//有大纲级别，是一条需求或者分类，新存一个reqbase
                Reqbase reqbase = new Reqbase();
                reqbase.setTitle(paragraph.getParagraphText());
                reqbase.setOutline(paraOutlineLvl.intValue());
                reqbases.add(reqbase);
            } else {//正文内容
                Reqbase reqbase = reqbases.peekLast();
                reqbase.setContent(reqbase.getContent() != null ? reqbase.getContent() : "" + paragraph.getParagraphText());
            }

            //有图片 解析图片
            if (!imageBundleList.isEmpty()) {
                for (String picId : imageBundleList) {
                    //建文件
                    String fileName = UUID.randomUUID().toString();
                    XWPFPictureData pictureData = document.getPictureDataByID(picId);
                    String suffix = pictureData.getFileName().substring(pictureData.getFileName().lastIndexOf("."));
                    fileName = fileName + suffix;

                    //存文件
                    if (Files.notExists(Paths.get("attaches"))) Files.createDirectory(Paths.get("attaches"));
                    Files.createFile(Paths.get("attaches", fileName));
                    Files.write(Paths.get("attaches", fileName), pictureData.getData());

                    //保存附件到需求
                    Attach attach = new Attach();
                    attach.setAname("图片 " + pictureData.getFileName());
                    attach.setApath("attaches" + File.separator + fileName);
                    Reqbase reqbase = reqbases.peekLast();
                    if (null == reqbase.getAttachList()) reqbase.setAttachList(new ArrayList<>());
                    reqbase.getAttachList().add(attach);
                }
            }

            //有其他嵌入文件 解析文件
            if (!objectBundleList.isEmpty()) {
                for (String objId : objectBundleList) {
                    //建文件
                    String fileName = UUID.randomUUID().toString();
                    POIXMLDocumentPart relationById = document.getRelationById(objId);
                    PackagePart packagePart = relationById.getPackagePart();//文件包
                    String suffix = packagePart.getPartName().toString().substring(packagePart.getPartName().toString().lastIndexOf("."));
                    fileName = fileName + suffix;


                    //存文件
                    if (Files.notExists(Paths.get("attaches"))) Files.createDirectory(Paths.get("attaches"));
                    Files.copy(packagePart.getInputStream(), Paths.get("attaches", fileName));

                    //保存附件到需求
                    Attach attach = new Attach();
                    attach.setAname("嵌入文件 " + packagePart.getPartName().toString());
                    attach.setApath("attaches" + File.separator + fileName);
                    Reqbase reqbase = reqbases.peekLast();
                    if (null == reqbase.getAttachList()) reqbase.setAttachList(new ArrayList<>());
                    reqbase.getAttachList().add(attach);
                }
            }

            //段落计数器加1
            counterMap.replace("curPara", ++curPara);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
