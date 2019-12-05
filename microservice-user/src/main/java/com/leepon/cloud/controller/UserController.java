package com.leepon.cloud.controller;

import com.leepon.cloud.annotation.EncryptField;
import com.leepon.cloud.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.leepon.cloud.entity.User;
import java.util.List;


@RequestMapping("/user")
@Slf4j
@RestController
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @RequestMapping(value = "/findUserById",method = RequestMethod.GET)
  @EncryptField(includes = {"username"},encrypt = false)
  public User findUserById(Integer userId) {
    log.info("=====访问UserApp=====");
    return userRepository.findOne(userId);
  }


  @RequestMapping(value = "/list",method = RequestMethod.POST)
  public List<User> findUserAll() {

    return userRepository.findAll();
  }
}
