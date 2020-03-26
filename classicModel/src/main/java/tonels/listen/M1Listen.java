package tonels.listen;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import tonels.model.DbExcel1;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class M1Listen extends AnalysisEventListener<DbExcel1> {

    private static final int BATCH_COUNT = 5;

    List<DbExcel1> list = new ArrayList<DbExcel1>();

    /**
     * When analysis one row trigger invoke function.
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(DbExcel1 data, AnalysisContext context) {

        log.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);

        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
//        if (list.size() >= BATCH_COUNT) {
//            saveData();
//            // 存储完成清理 list
//            list.clear();
//        }

    }

    /**
     * if have something to do after all analysis
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("所有数据解析完成！",JSON.toJSONString(list));
    }
}
