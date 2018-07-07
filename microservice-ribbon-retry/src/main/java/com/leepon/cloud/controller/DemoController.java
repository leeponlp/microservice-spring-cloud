package com.leepon.cloud.controller;

import com.alibaba.fastjson.JSON;
import com.leepon.cloud.entity.User;
import com.leepon.cloud.feign.UserFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/demo")
public class DemoController {

  @Autowired
  private UserFeignClient userFeignClient;


    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id) {
      User user = userFeignClient.findById(id);
      log.info("RPC调用返回：{}",JSON.toJSONString(user));
      return user;
  }


}
