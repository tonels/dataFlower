package common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.DateTimeException;
import java.util.List;

/**
 * @ClassName GlobalDefultExceptionHandler
 * @Description 用于捕获全局异常
 * @Author wzq
 * @Date 2019/7/23 17:44
 * @Version 1.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalDefultExceptionHandler {
    private static final String SYSTEM_OTHER_EXCEPTION = "系统其他异常";
    private static final String LESS_REQUIED_PARAM = "缺少必要参数";
    private static final String QUERY_ENTITYNOTFOUNDEXCEPTION = "未找到任何相关结果";
    private static final String REQUEST_PARAMS_IS_NOT_ILLEGAL = "请求参数不合法";
    private static final String REQUEST_PARAMS_IS_NOT_EMPTY = "参数不能为空";
    private static final String REQUEST_PARAMS_MISMATCH = "请求参数类型不匹配";
    private static final String REQUEST_PARAMS_NUMBER_FORMAT_IS_ERROR = "请求参数数字类型解析异常";
    private static final String REQUEST_METHOD_NOT_SUPPORT = "当前requestMethod请求类型不支持";
    private static final String REQUEST_MULTIPARTFILE_IS_NOT_EMPTY = "当前请求multipartfile文件不能为空";
    private static final String Date_Time_Exception = "时间格式错误";

//    /**
//     * 其他异常处理
//     *
//     * @param e Exception
//     * @return ResponseEntity<ResultBean>
//     */
//    @ResponseBody
//    @ExceptionHandler(value = Exception.class)
//    public ResultBean cacheError(Exception e) {
//        return responseException(SYSTEM_OTHER_EXCEPTION,e);
//    }

    @ExceptionHandler({BusinessException.class})
    @ResponseStatus(HttpStatus.OK)
    public ResultBean businessException(BusinessException ex) {
        log.error(ex.getErrorCode() + " " + ex.getMessage());
        return ResultBean.error(ex.getErrorCode() + "", ex.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResultBean methodArgumentNotValidException(HttpServletResponse response, HttpServletRequest request, MethodArgumentNotValidException e) {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        if (!CollectionUtils.isEmpty(allErrors)) {
            return responseException(allErrors.get(0).getDefaultMessage(), e);
        }
        return responseException(LESS_REQUIED_PARAM, e);
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResultBean handlerHttpRequestMethodNotSupportedException(HttpServletResponse response, HttpServletRequest request, HttpRequestMethodNotSupportedException e) {
        return responseException(REQUEST_METHOD_NOT_SUPPORT, e);
    }

    @ExceptionHandler(value = NumberFormatException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResultBean handlerNumberFormatException(HttpServletResponse response, HttpServletRequest request, NumberFormatException e) {
        return responseException(REQUEST_PARAMS_NUMBER_FORMAT_IS_ERROR, e);
    }


    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResultBean handlerMethodArgumentTypeMismatchException(HttpServletResponse response, HttpServletRequest request, MethodArgumentTypeMismatchException e) {
        String name = e.getName();
        return responseException(name + REQUEST_PARAMS_MISMATCH, e);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResultBean handleValidationException(ConstraintViolationException e) {
        for (ConstraintViolation<?> s : e.getConstraintViolations()) {
            return responseException(s.getMessage() + " : " + s.getInvalidValue(), e);
        }
        return ResultBean.error(REQUEST_PARAMS_IS_NOT_ILLEGAL);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResultBean handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        String parameterName = e.getParameterName();
        return responseException(parameterName + REQUEST_PARAMS_IS_NOT_EMPTY, e);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResultBean HttpMessageNotReadableException(HttpServletResponse response, HttpServletRequest request, HttpMessageNotReadableException e) {
        return responseException(REQUEST_PARAMS_MISMATCH, e);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = MissingServletRequestPartException.class)
    public ResultBean missingServletRequestPartException(HttpServletResponse response, HttpServletRequest request, MissingServletRequestPartException e) {
        return responseException(REQUEST_MULTIPARTFILE_IS_NOT_EMPTY, e);
    }


    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = DateTimeException.class)
    public ResultBean dateTimeException(DateTimeException e) {
        return responseException(Date_Time_Exception, e);
    }

    private ResultBean responseException(String msg, Exception e) {
        log.error(e.getMessage(), e);
        return ResultBean.error(msg);
    }
}
