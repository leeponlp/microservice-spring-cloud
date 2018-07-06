package com.leepon.cloud.controller;

import com.leepon.cloud.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @task:
 * @discrption:
 * @author: 苏小城
 * @date: 17/12/26
 * @version: 1.0.0
 */

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/{id}")
    @HystrixCommand(fallbackMethod = "findByIdFallback")
    public User findById(@PathVariable Integer id) {
        return this.restTemplate.getForObject("http://microservice-user/user/" + id, User.class);
    }

    public User findByIdFallback(Integer id) {
        User user = new User();
        user.setId(0);
        return user;
    }

}
