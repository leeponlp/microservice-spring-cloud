package com.leepon.cloud.controller;

import com.leepon.cloud.annotation.EncryptField;
import com.leepon.cloud.repository.UserRepository;
import com.leepon.cloud.service.IUserService;
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
public class UserController implements IUserService {

  @Autowired
  private UserRepository userRepository;


  @EncryptField(includes = {"username"})
  @Override
  public User findById(@PathVariable Integer id) {
    log.info("=====访问UserApp=====");
    return userRepository.findOne(id);
  }


  @Override
  public List<User> findUserAll() {

    return userRepository.findAll();
  }
}