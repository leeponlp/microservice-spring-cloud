package com.suxiaocheng.framework.xss;


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
