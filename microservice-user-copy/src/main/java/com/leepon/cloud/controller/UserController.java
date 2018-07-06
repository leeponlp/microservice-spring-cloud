package com.leepon.cloud.controller;

import com.leepon.cloud.annotation.EncryptField;
import com.leepon.cloud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.leepon.cloud.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserRepository userRepository;


  @EncryptField(includes = {"username"})
  @RequestMapping("/{id}")
  public User findById(@PathVariable Integer id) {
    int i = 1/0;
    return userRepository.findOne(id);
  }


  @RequestMapping("/list")
  public List<User> findUserAll() {

    return userRepository.findAll();
  }
}
