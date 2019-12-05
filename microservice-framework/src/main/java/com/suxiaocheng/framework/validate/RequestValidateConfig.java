package com.suxiaocheng.framework.validate;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "request.format.validate")
@Getter
@Setter
public class RequestValidateConfig {

    /***
     * 是否校验Field
     */
    private Boolean validateField;

    /***
     * 是否开启
     */
    private Boolean enabled;
}
