package com.xwy.poi.utils;

import com.microsoft.schemas.office.office.CTOLEObject;
import com.microsoft.schemas.vml.CTShape;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.openxmlformats.schemas.drawingml.x2006.main.CTGraphicalObject;
import org.openxmlformats.schemas.drawingml.x2006.picture.CTPicture;
import org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTInline;


import org.openxmlformats.schemas.presentationml.x2006.main.CTOleObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTObject;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: XWPFUtils
 * Function:  TODO
 * Date:      2020/2/12 0012 21:09
 * author     XieWenYing
 * version    V1.0
 */
public class XWPFUtils {

    /**
     * 获取某一段落中图片和对象的索引
     * @param paragraph
     * @return
     */
    public static Map<String, List<String>> readAttachInParagraph(XWPFParagraph paragraph) {

        //图片索引和Object索引获取map
        HashMap map = new HashMap<>();

        //图片索引List
        List<String> imageBundleList = new ArrayList<>();
        //Object索引List
        ArrayList<String> objectBundleList = new ArrayList<>();

        List<XWPFRun> runList = paragraph.getRuns();

        for (XWPFRun run : runList) {

            CTR ctr = run.getCTR();
            //对子元素进行遍历
            XmlCursor xmlCursor = ctr.newCursor();
            //拿到所有子元素
            xmlCursor.selectPath("./*");
            while (xmlCursor.toNextSelection()) {

                XmlObject o = xmlCursor.getObject();
                //如果子元素是<w:drawing>这样的形式， 使用CTDrawing保存图片
                if (o instanceof CTDrawing) {
                    CTDrawing drawing = (CTDrawing) o;
                    List<CTInline> ctInlines = drawing.getInlineList();
                    for (CTInline inline : ctInlines) {
                        CTGraphicalObject graphic = inline.getGraphic();
                        XmlCursor cursor = graphic.getGraphicData().newCursor();
                        cursor.selectPath("./*");
                        while (cursor.toNextSelection()) {
                            XmlObject object = cursor.getObject();
                            //如果子元素是<pic:pic>这种形式
                            if (object instanceof CTPicture) {
                                CTPicture picture = (CTPicture) object;
                                //拿到元素的属性
                                imageBundleList.add(picture.getBlipFill().getBlip().getEmbed());
                            }
                        }

                    }

                }
                //使用CTObject保存图片
                //<w:object>形式
                if (o instanceof CTObject) {
                    CTObject object = (CTObject) o;
                    XmlCursor cursor = object.newCursor();
                    cursor.selectPath("./*");
                    CTShape shape;
                    CTOLEObject oleObject;

                    while (cursor.toNextSelection()) {
                        XmlObject xmlObject = cursor.getObject();
                        //如果是图片类型，存图片id
                        if (xmlObject instanceof CTShape) {
                            shape = (CTShape) xmlObject;

                            imageBundleList.add(shape.getImagedataArray(0).getId2());
                        }
                        //如果是嵌入对象类型，存对象id
                        if (xmlObject instanceof CTOLEObject) {
                            oleObject = (CTOLEObject) xmlObject;
                            objectBundleList.add(oleObject.getId());
                        }
                    }

                }
            }
        }
        map.put("img", imageBundleList);
        map.put("object", objectBundleList);
        return map;
    }

    /**
     * 获取某一段落的大纲级别
     * @param document
     * @param paragraph
     * @return
     */
    public static BigInteger getParaOutlineLvl(XWPFDocument document, XWPFParagraph paragraph) {

        XWPFStyles styles = document.getStyles();
        XWPFStyle style = styles.getStyle(paragraph.getStyle());
        //判断该段落是否设置了大纲级别
        if (paragraph.getCTP().getPPr().getOutlineLvl() != null) {
            System.out.println(paragraph.getParagraphText());
            System.out.println(paragraph.getCTP().getPPr().getOutlineLvl().getVal());
            return paragraph.getCTP().getPPr().getOutlineLvl().getVal();

            //判断该段落的样式是否设置了大纲级别
        } else if (style != null && style.getCTStyle().getPPr().getOutlineLvl() != null) {
            System.out.println(paragraph.getParagraphText());
            System.out.println(style.getCTStyle().getPPr().getOutlineLvl().getVal());
            return style.getCTStyle().getPPr().getOutlineLvl().getVal();

            //判断该段落的基础样式是否设置了大纲级别
        } else if (style != null && styles.getStyle(style.getCTStyle().getBasedOn().getVal())
                .getCTStyle().getPPr().getOutlineLvl() != null) {
            System.out.println(paragraph.getParagraphText());
            String styledName = style.getCTStyle().getBasedOn().getVal();
            System.out.println(styles.getStyle(styledName).getCTStyle().getPPr().getOutlineLvl().getVal());
            return styles.getStyle(styledName).getCTStyle().getPPr().getOutlineLvl().getVal();

            //没有设置大纲级别
        } else {
            return null;
        }
    }

}
