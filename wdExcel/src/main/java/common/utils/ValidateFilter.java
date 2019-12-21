package common.utils;

import common.constant.Constants;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Title: 验证数据
 * Description: ValidateUtils
 * Date: 2019年02月25日
 *
 * @author douzi
 * @version 1.0.0
 * @since jdk8
 */

@Slf4j
public class ValidateFilter {

    private static Validator sValidator;

    static {
        sValidator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校验数据 获取过滤值
     *
     * @param obj  数据
     * @param type 类型
     * @param <T>  泛型
     * @return 校验结果
     */
    public static <T> List<String> getFilterMessage(T obj, String type) {
        Set<ConstraintViolation<T>> validate = new HashSet<>();
        List<String> messageList = new ArrayList<>();
        switch (type) {
            case Constants.VALIDATE_DEFAULT:
                validate = sValidator.validate(obj, Default.class);
                break;
            case Constants.VALIDATE_UPDATE:
                validate = sValidator.validate(obj, GroupType.Update.class);
                break;
            case Constants.VALIDATE_DEL:
                validate = sValidator.validate(obj, GroupType.Delete.class);
                break;
            case Constants.VALIDATE_FIND:
                validate = sValidator.validate(obj, GroupType.find.class);
                break;
            default:
        }
        if (!validate.isEmpty()) {
            for (ConstraintViolation<T> content : validate) {
                log.info(Constants.VALIDATE_ERROR, content.getMessage());
                messageList.add(content.getMessage());
            }
            return messageList;
        }
        return messageList;
    }

    /**
     * <p><b>Title:</b> getFilterMessage</p>
     * <p><b>Description:</b> 默认校验规则</p>
     *
     * @param obj
     * @return
     * @author douzi
     */
    public static <T> List<String> getFilterMessage(T obj) {
        List<String> messageList = new ArrayList<>();
        Set<ConstraintViolation<T>> validate = sValidator.validate(obj);

        if (!validate.isEmpty()) {
            for (ConstraintViolation<T> content : validate) {
                log.info(Constants.VALIDATE_ERROR, content.getMessage());
                messageList.add(content.getMessage());
            }
            return messageList;
        }
        return messageList;
    }

}
