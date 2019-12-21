package common.exception;

/**
 * <p><b>BusinessException Description:</b> 业务类运行时异常, 所有业务类运行时异常继承于该异常</p>
 *
 * @author douzi
 * <b>DATE</b> 2019年2月18日 上午10:27:00
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    protected int errorCode;

    public BusinessException(int errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @param message
     * @param cause
     */
    public BusinessException(int errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    /**
     * @param message
     */
    public BusinessException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * @param cause
     */
    public BusinessException(int errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return this.errorCode;
    }
}
