package common.utils.excel.fieldtype;

/**
 * <p><b>AbstractFieldType Description:</b> excel导入导出非字典列，id和value互转抽象类</p>
 * @author douzi
 * <b>DATE</b> 2019年8月13日 下午12:53:30
 */
public abstract class AbstractFieldType {
	/**
	 * <p><b>Title:</b> getValue</p>
	 * <p><b>Description:</b> 获取</p>
	 * @author douzi
	 * @param val
	 * @return
	 */
	public abstract Object getValue(String val);
	
	/**
	 * <p><b>Title:</b> setValue</p>
	 * <p><b>Description:</b> 设置</p>
	 * @author douzi
	 * @param val
	 * @return
	 */
	public abstract String setValue(Object val);
}
