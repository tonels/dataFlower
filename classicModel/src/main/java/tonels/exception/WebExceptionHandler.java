package tonels.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tonels.util.ResultBean;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.Set;
import java.util.stream.Collectors;

//import org.apache.commons.configuration.ConversionException;

/**
 * 拦截异常,并统一处理
 *
 * @author ls
 */
@RestControllerAdvice
@Slf4j
public class WebExceptionHandler {

    /**
     * 非法参数
     *
     * @param e
     * @return
     */
    @ExceptionHandler({IllegalArgumentException.class})
    public Object illegalArgumentException(Exception e) {
        log.error(">>>异常：{}", e.getMessage());
        e.printStackTrace();
        return ResultBean.error(HttpStatus.BAD_REQUEST.value() + "", e.getMessage());
    }

    /**
     * 不可解析的数据格式
     *
     * @param e
     * @return
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public Object httpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error(">>>异常：{}", e.getMessage());
        e.printStackTrace();
        return ResultBean.error(HttpStatus.BAD_REQUEST.value() + "", e.getMessage());
    }

    /**
     * 校验错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Object constraintViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> validations = e.getConstraintViolations();
        log.info(">>>异常：{}::{} 参数校验错误", e.getStackTrace()[0].getClassName(), e.getStackTrace()[0].getMethodName());
        e.printStackTrace();

        StringBuilder buffer = new StringBuilder();
        for (ConstraintViolation<?> validation : validations) {
            buffer.append(validation.getMessage());
        }
        return ResultBean.error(HttpStatus.BAD_REQUEST.value() + "", buffer.toString());
    }

    /**
     * spring 参数校验错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object methodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining("; "));
        log.error(e.getMessage());
        e.printStackTrace();
        return ResultBean.error(HttpStatus.BAD_REQUEST.value() + "", message);
    }

    /**
     * 请求资源未找到
     *
     * @param e
     */
    @ExceptionHandler(NotFoundException.class)
    public Object notFoundException(NotFoundException e) {
        log.error(">>>异常：{}", e.getMessage());
        e.printStackTrace();
        return ResultBean.error(HttpStatus.NOT_FOUND.value() + "", e.getMessage());
    }

    /**
     * 请求方式错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Object handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error(">>>异常：{}", e.getMessage());
        e.printStackTrace();
        return ResultBean.error(HttpStatus.METHOD_NOT_ALLOWED.value() + "", e.getMessage());
    }

    /**
     * 不支持请求
     *
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Object httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        log.error(">>>异常：{}", e.getMessage());
        e.printStackTrace();
        return ResultBean.error(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value() + "", e.getMessage());
    }

    /**
     * io异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(IOException.class)
    public Object ioException(IOException e) {
        log.error(">>>异常：{}", e.getMessage());
        e.printStackTrace();
        return ResultBean.error(HttpStatus.BAD_REQUEST.value() + "", e.getMessage());
    }

    /**
     * 状态异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(IllegalStateException.class)
    public Object illegalArgumentException1(IllegalStateException e) {
        log.error(">>>异常：{}", e.getMessage());
        e.printStackTrace();
        return ResultBean.error(HttpStatus.BAD_REQUEST.value() + "", e.getMessage());
    }

    /**
     * 运行时异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public Object runtimeException(RuntimeException e) {
        log.error(">>>异常：{}", e.getMessage());
        e.printStackTrace();
        return ResultBean.error(HttpStatus.BAD_REQUEST.value() + "", e.getMessage());
    }

    // @ExceptionHandler(value = CreditAppException.class)
    // public Object creditAppException(CreditAppException e,HttpServletResponse
    // response){
    // log.error(e.getMessage(), e);
    // return ResultBean.error(e.getCode()+"", "服务器异常请稍后再试");
    // }

    /**
     * 空指针
     *
     * @param e
     * @param response
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    public Object NullPointerException(NullPointerException e, HttpServletResponse response) {
        log.error(">>>异常：{}", e.getMessage());
        e.printStackTrace();
        return ResultBean.error(HttpStatus.BAD_REQUEST.value() + "", e.getMessage());
    }

    /**
     * 业务异常
     *
     * @param e
     * @param response
     * @return
     */
    @ExceptionHandler(value = ServiceException.class)
    public Object serviceException(ServiceException e, HttpServletResponse response) {
        log.error(">>>异常：{}", e.getMsg());
        e.printStackTrace();
        return ResultBean.error(HttpStatus.BAD_REQUEST.value() + "", e.getMsg());
    }

    /**
     * mybatis sql 异常
     *
     * @param e
     * @param response
     * @return
     */
    @ExceptionHandler(value = SQLSyntaxErrorException.class)
    public Object SQLSyntaxErrorException(SQLSyntaxErrorException e, HttpServletResponse response) {
        log.error(">>>异常：{}", e.getMessage());
        e.printStackTrace();
        return ResultBean.error(HttpStatus.BAD_REQUEST.value() + "", e.getMessage());
    }

    /**
     * mybatis sql 异常
     *
     * @param e
     * @param response
     * @return
     */
    @ExceptionHandler(value = SQLException.class)
    public Object SQLException(SQLException e, HttpServletResponse response) {
        log.error(">>>异常：{}", e.getMessage());
        e.printStackTrace();
        return ResultBean.error(HttpStatus.BAD_REQUEST.value() + "", e.getMessage());
    }

    /**
     * Exception 异常
     *
     * @param e
     * @param response
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Object exception(Exception e, HttpServletResponse response) {
        log.error(">>>异常：{}", e.getMessage());
        e.printStackTrace();
        return ResultBean.error(HttpStatus.BAD_REQUEST.value() + "", e.getMessage());
    }

    /**
     * Exception 异常
     *
     * @param e
     * @param response
     * @return
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public Object IndexOutOfBoundsException(IndexOutOfBoundsException e, HttpServletResponse response) {
        log.error(">>>异常：{}", e.getMessage());
        e.printStackTrace();
        return ResultBean.error(HttpStatus.INTERNAL_SERVER_ERROR.value() + "", e.getMessage());
    }

}
