/**
 * <p><b>Title:</b> DictUtils.java </p>
 *
 * @Package com.wondertek.oes.charge.service.utils
 */
package common.cache;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p><b>DictUtils Description:</b> 字典表获取方法，给excle导入、导出使用</p>
 *
 * @author douzi
 * <b>DATE</b> 2019年8月14日 下午5:18:24
 */
@Component
public class DictUtils extends AbstractDictUtils {

    @Override
    public String getDictLabel(String value, String type, String defaultValue) {
        return null;
    }

    /**
     * <p><b>Title:</b> getDictList</p>
     * <p><b>Description:</b> 缓存数据2分钟，让请求字典次数变少，内网查询仍然耗费时间，如果导入导出大量数据，仍然耗时严重</p>
     *
     * @param type
     * @return
     * @author douzi
     */
    public List getDictList(String type) {
        return null;
    }

    @Override
    public String getDictValue(String label, String type, String defaultLabel) {
        return null;
    }

    /**
     * p><b>Title:</b> transDictCodesToLabels</p>
     * <p><b>Description:</b>将多个字典code值字符串转化为label列表字符串</p>
     *
     * @param codes
     * @param type
     * @return
     */
    public String transDictCodesToLabels(String codes, String type) {
        StringBuffer sb = new StringBuffer("");
        if (StringUtils.isNotBlank(codes)) {
            String[] strArray = codes.split(",");
            for (String s : strArray) {
                sb.append(getDictLabel(s, type, "")).append("  ");
            }
        }
        return sb.toString();
    }

}
