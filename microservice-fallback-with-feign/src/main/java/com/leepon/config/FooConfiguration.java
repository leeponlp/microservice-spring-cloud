package com.leepon.config;

import feign.Feign;
import feign.Request;
import feign.Retryer;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import feign.Logger;
import org.springframework.context.annotation.Scope;

@Configuration
public class FooConfiguration {

  @Bean
  @Scope("prototype")
  public Feign.Builder feignBuilder() {
    return Feign.builder()
            .encoder(new JacksonEncoder())
            .decoder(new JacksonDecoder())
            .options(new Request.Options(1000, 3500))
            .retryer(new Retryer.Default(5000, 5000, 3));

  }

  @Bean
  Logger.Level feignLoggerLevel() {
    return Logger.Level.FULL;
  }
}
