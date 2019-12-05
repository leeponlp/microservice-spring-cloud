package com.suxiaocheng.framework.validate.annotation;

import com.suxiaocheng.framework.validate.RequestFieldValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,
        ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RequestFieldValidator.class)
public @interface RequestFieldValidate {

    /**
     * 不符合时提拱的异常内容
     *
     * @return
     */
    String message() default "";

    /**
     * 规则Key
     *
     * @return
     */
    String key() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
