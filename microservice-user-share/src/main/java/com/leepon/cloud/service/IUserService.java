package com.leepon.cloud.service;

import com.leepon.cloud.entity.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @Description TODO
 * @Author admin
 * @Date 2018/7/7 下午10:35
 * @Version 1.0
 */
@RequestMapping("/user")
public interface IUserService {

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    User findById(@PathVariable(value = "id") Integer id);


    @RequestMapping(value = "/list",method = RequestMethod.POST)
    List<User> findUserAll();
}
