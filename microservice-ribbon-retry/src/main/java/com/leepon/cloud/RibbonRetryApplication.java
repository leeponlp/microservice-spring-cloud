package com.leepon.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker //开启断路器
@EnableRetry
@EnableDiscoveryClient
public class RibbonRetryApplication {

  @Bean
  @ConfigurationProperties(prefix = "retry")
  public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory(){
    HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory=new HttpComponentsClientHttpRequestFactory();
    return httpComponentsClientHttpRequestFactory;
  }



  @Bean
  @LoadBalanced
  public RestTemplate restTemplate() {
    return new RestTemplate(httpComponentsClientHttpRequestFactory());
  }


  public static void main(String[] args) {
    SpringApplication.run(RibbonRetryApplication.class, args);
  }
}
