package com.tonels.core.celldata;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;

/**
 * @author Jiaju Zhuang
 */
public class CellDataDataListener extends AnalysisEventListener<CellDataData> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CellDataDataListener.class);
    List<CellDataData> list = new ArrayList<CellDataData>();

    @Override
    public void invoke(CellDataData data, AnalysisContext context) {
        list.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        Assert.assertEquals(list.size(), 1);
        CellDataData cellDataData = list.get(0);

        String stringValue = cellDataData.getDate().getStringValue();
        Integer data = cellDataData.getInteger1().getData();
        Integer integer2 = cellDataData.getInteger2();

        String formulaValue = cellDataData.getFormulaValue().getFormulaValue(); // B2+C2
        LOGGER.debug("First row:{}", JSON.toJSONString(list.get(0)));
    }
}
