package listen;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import model.DbVo1;
import model.TwoTType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class TdateTypeListen extends AnalysisEventListener<TwoTType> {


    private static final int BATCH_COUNT = 5;

    List<TwoTType> list = new ArrayList<TwoTType>();


    /**
     * When analysis one row trigger invoke function.
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(TwoTType data, AnalysisContext context) {

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
     * 这里解析数据后，转换对象会有异常出现（空指针），
     * todo 根据具体业务 ，在做解决
     * @param context
     */

    @Override
    @SneakyThrows/*(NullPointerException.class)*/
    public void doAfterAllAnalysed(AnalysisContext context) {
        log.info("所有数据解析完成！" +  list.size());
        log.info("开始转换对象....");
        List<DbVo1> list1 = null;
            list1 = list.stream().map(DbVo1::new).collect(Collectors.toList());

        log.info("转换对象结束...." + JSON.toJSONString(list1));
    }
}
