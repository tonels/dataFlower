package common.utils;

/**
 * <p><b>AbstractDictUtils Description:</b> excel中获取字典的抽象类</p>
 * @author douzi
 * <b>DATE</b> 2019年8月13日 下午1:00:40
 */
public abstract class AbstractDictUtils {
	
	/**
	 * <p><b>Title:</b> getDictLabel</p>
	 * <p><b>Description:</b> 通过值获取字典的标签</p>
	 * @author douzi
	 * @param value
	 * @param type
	 * @param defaultValue
	 * @return
	 */
	public abstract String getDictLabel(String value, String type, String defaultValue);
	
	/**
	 * <p><b>Title:</b> getDictLabel</p>
	 * <p><b>Description:</b> 通过标签获取字典的值</p>
	 * @author douzi
	 * @param label
	 * @param type
	 * @param defaultLabel
	 * @return
	 */
	public abstract String getDictValue(String label, String type, String defaultLabel);
}
