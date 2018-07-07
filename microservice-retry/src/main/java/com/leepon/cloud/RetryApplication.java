package com.leepon.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableRetry
public class RetryApplication {

  public static void main(String[] args) {
    SpringApplication.run(RetryApplication.class, args);
  }
}
