/*
 * Copyright By ZATI
 * Copyright By 3a3c88295d37870dfd3b25056092d1a9209824b256c341f2cdc296437f671617
 * All rights reserved.
 *
 * If you are not the intended user, you are hereby notified that any use, disclosure, copying, printing, forwarding or
 * dissemination of this property is strictly prohibited. If you have got this file in error, delete it from your system.
 */
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
     * 验证是kaku具体规则Key
     *
     * @return
     */
    String key() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
