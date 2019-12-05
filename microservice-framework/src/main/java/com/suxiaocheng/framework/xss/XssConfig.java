package com.suxiaocheng.framework.xss;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "xss.validate")
@Data
public class XssConfig {

    private Boolean enabled;

}
