/*
  * Copyright By ZATI
  * Copyright By 3a3c88295d37870dfd3b25056092d1a9209824b256c341f2cdc296437f671617
  * All rights reserved.
  *
  * If you are not the intended user, you are hereby notified that any use, disclosure, copying, printing, forwarding or
  * dissemination of this property is strictly prohibited. If you have got this file in error, delete it from your system.
  */
package com.suxiaocheng.framework.xss;

/**
 * 类 XssFilterAutoConfig 的实现描述：xss配置类
 *
 * @author za-qilifan001 2019/11/22 16:26
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ConditionalOnClass({FilterRegistrationBean.class})
@Slf4j
public class XssFilterAutoConfig {

    @Value("#{'${xss.validate.uri:/*}'.split(',')}")
    private String[] xssFilterUriList;

    @Value("${xss.validate.headerKey:}")
    private String validateHeaderKey;

    public XssFilterAutoConfig() {
    }

    @Bean
    @Primary
    @ConditionalOnProperty(prefix = "xss.validate", value = {"enabled"}, havingValue = "true")
    public FilterRegistrationBean xssFilterRegister() {
        log.info("XssFilterAutoConfig init xssFilter");
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new XssFilter());
        registration.addUrlPatterns(xssFilterUriList);
        registration.addInitParameter("validateHeaderKey", validateHeaderKey);


        registration.setName("xssFilter");
        registration.setOrder(1);
        return registration;
    }
}
