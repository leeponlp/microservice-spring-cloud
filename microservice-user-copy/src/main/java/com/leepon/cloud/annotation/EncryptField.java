package com.leepon.cloud.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EncryptField {
    /**
     * 需要处理的字段
     *
     * @return
     */
    String[] includes() default {};

    /**
     * 需要处理的字段数据是否需要加密
     *
     * @return
     */
    boolean encrypt() default true;

    /**
     * 是否对全局加密
     * @return
     */
    boolean global() default false;
}
