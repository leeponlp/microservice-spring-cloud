package com.leepon.cloud.controller;

import com.leepon.cloud.entity.User;
import com.leepon.cloud.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/product")
public class DemoController {

  @Autowired
  private UserFeignClient userFeignClient;


    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id) {
      User user = userFeignClient.findById(id);
      return user;
  }


}
