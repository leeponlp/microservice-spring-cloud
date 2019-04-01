package com.leepon.cloud.controller;

import com.leepon.cloud.dto.User;
import com.leepon.cloud.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName LoginController
 * @Description TODO...
 * @Author 苏小城
 * @Date 2019-4-1 11:08
 * @Version 1.0
 */
@Slf4j
@RequestMapping("/user")
@RestController
public class LoginController {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/signUp",method = RequestMethod.POST)
    public User signUp(@RequestBody User user){
        User bizUser = userRepository.findByUsername(user.getUsername());
        if(null != bizUser){
            throw new RuntimeException("用户已经存在");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
