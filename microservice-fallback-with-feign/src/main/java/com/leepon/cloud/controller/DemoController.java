package com.leepon.cloud.controller;

import com.leepon.cloud.entity.User;
import com.leepon.cloud.feign.IOuterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/demo")
public class DemoController {

  @Autowired
  private IOuterUserService outerUserService;

//  @Autowired
//  private UserFeignClient   userFeignClient;

    @GetMapping("/{id}")
    public User findById(@PathVariable Integer id) {
      User user = outerUserService.findById(id);
      return user;
  }



}
