package com.leepon.cloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author 苏小城
 * @Date 2019/3/31 4:11 PM
 * @Version 1.0
 */
@RequestMapping("/resource")
@RestController
public class ResourceController {

    @Autowired
    UserDetailsService  userDetailsService;

    @RequestMapping("/hello")
    public String helloWorld(){
        return "Hello World!";
    }
}
