/* 
  * Copyright By ZATI
  * Copyright By 3a3c88295d37870dfd3b25056092d1a9209824b256c341f2cdc296437f671617
  * All rights reserved.
  *
  * If you are not the intended user, you are hereby notified that any use, disclosure, copying, printing, forwarding or 
  * dissemination of this property is strictly prohibited. If you have got this file in error, delete it from your system. 
  */
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
