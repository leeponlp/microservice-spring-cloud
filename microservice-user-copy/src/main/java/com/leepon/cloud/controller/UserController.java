package com.leepon.cloud.controller;

import com.leepon.cloud.repository.UserRepository;
import com.leepon.cloud.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.leepon.cloud.entity.User;
import java.util.List;

@Slf4j
@RestController
public class UserController implements IUserService {

  @Autowired
  private UserRepository userRepository;


  @Override
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
  @Override
  public List<User> findUserAll() {

    return userRepository.findAll();
  }
}
