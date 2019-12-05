/* 
  * Copyright By ZATI
  * Copyright By 3a3c88295d37870dfd3b25056092d1a9209824b256c341f2cdc296437f671617
  * All rights reserved.
  *
  * If you are not the intended user, you are hereby notified that any use, disclosure, copying, printing, forwarding or 
  * dissemination of this property is strictly prohibited. If you have got this file in error, delete it from your system. 
  */
package com.suxiaocheng.framework.validate;

import com.suxiaocheng.framework.ApplicationContextHolderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.util.Set;

/**
 * 类 ValidateDtoContexUtil 的实现描述：校验DTO
 *
 * @author za-qilifan001 2019/11/25 21:38
 */
@Component
@Slf4j
public class ValidateUtil {
    @Autowired
    private Validator validator;
    @Autowired
    private RequestValidateConfig validateConfig;

    public <T> String validate(T t) throws ValidationException {
        String message = "";

        if (!validateConfig.getEnabled()) {
            return message;
        }
        Set<ConstraintViolation<T>> set = validator.validate(t);
        if (set.size() > 0) {
            ConstraintViolation<T> next = set.iterator().next();
            Path propertyPath = next.getPropertyPath();
            log.info("propertyPath field is {}", propertyPath);
            return next.getMessage();
        }
        return message;
    }

    public static ValidateUtil getInstance() {
        return ApplicationContextHolderUtil.get().getBean(ValidateUtil.class);
    }


}
