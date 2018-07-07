package com.leepon.cloud.controller;

import com.leepon.cloud.entity.User;
import com.leepon.cloud.service.IDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Recover;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/demo")
public class DemoController {

  @Autowired
  private IDemoService demoService;


    @RequestMapping("/{id}")
    public User findById(@PathVariable Integer id) {
      User user = demoService.findById(id);
      return user;
  }

  @Recover
  public User recover(Exception e) {
    User user = new User();
    user.setId(0);
    return user;
  }



}
