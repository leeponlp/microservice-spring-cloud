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
