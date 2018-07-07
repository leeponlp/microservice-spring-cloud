package com.leepon.cloud.controller;

import com.leepon.cloud.annotation.EncryptField;
import com.leepon.cloud.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.leepon.cloud.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserRepository userRepository;


  @EncryptField(includes = {"username"})
  @RequestMapping("/{id}")
  public User findById(@PathVariable Integer id) {
    log.info("=====访问CopyUserApp=====");
    try{
      Thread.sleep(5000);
    }catch (Exception e){
      log.error("线程异常：{}",e);
    }
    return userRepository.findOne(id);
  }


  @RequestMapping("/list")
  public List<User> findUserAll() {

    return userRepository.findAll();
  }
}
