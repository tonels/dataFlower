package tonels.exception;

import lombok.Data;

@Data
public class ServiceException extends RuntimeException{

    private String code;

    private String msg;


    public ServiceException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ServiceException(String msg) {
        this.msg = msg;
    }
}
