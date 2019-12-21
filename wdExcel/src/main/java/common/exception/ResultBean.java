package common.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultBean<T> {
    /**
     * 信息代码
     */
    private String code;
    /**
     * 信息说明
     */
    private String msg;
    /**
     * 返回数据或jqgrid中的root
     */
    private T result;

    public ResultBean() {
    }

    public ResultBean(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultBean(String code, String msg, T result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    public static <T> ResultBean<T> ok() {
        return new ResultBean<>(GlobalCodeEnum.SUCCESS.getCode(), GlobalCodeEnum.SUCCESS.getMsg());
    }

    public static <T> ResultBean<T> ok(T result) {
        return new ResultBean<T>(GlobalCodeEnum.SUCCESS.getCode(), GlobalCodeEnum.SUCCESS.getMsg(), result);
    }

    public Boolean isSuccess() {
        return GlobalCodeEnum.SUCCESS.getCode().equals(this.code);
    }

    public static <T> ResultBean<T> error() {
        return new ResultBean<T>(GlobalCodeEnum.FAILURE.getCode(), GlobalCodeEnum.FAILURE.getMsg());
    }

    public static <T> ResultBean<T> error(String msg) {
        return new ResultBean<T>(GlobalCodeEnum.FAILURE.getCode(), msg);
    }

    public static <T> ResultBean<T> error(String code, String msg) {
        return new ResultBean<T>(code, msg);
    }

}
