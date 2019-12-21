package common.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p><b>MethodLog Description:</b> controller层aop日志记录</p>
 * @author douzi
 * <b>DATE</b> 2019年6月27日 上午9:04:07
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodLog {
	/**
	 * <p><b>Title:</b> module</p>
	 * <p><b>Description:</b> 日志模块</p>
	 * @author douzi
	 * @return
	 */
	String module();

	/**
	 * <p><b>Title:</b> recordParam</p>
	 * <p><b>Description:</b> 记录参数 尽量记录普通参数类型的方法，和能序列化的对象</p>
	 * @author douzi
	 * @return
	 */
	boolean recordParam() default true;

	/**
	 * <p><b>Title:</b> opterate</p>
	 * <p><b>Description:</b> 操作状态 1:新增 2:修改 3:删除 4:发布 5 撤回 6：导入 7：导出 8：模板下载 9:媒资入包 10:启动 11：禁止 12: 一键移包 </p>
	 * @return
	 * @author douzi
	 */
	int opterate() default 1;

	/**
	 * <p><b>Title:</b> insertDB</p>
	 * <p><b>Description:</b> 是否保存进操作日志表，默认为不保存</p>
	 *
	 * @return
	 * @author douzi
	 */
	boolean insertDB() default false;
}