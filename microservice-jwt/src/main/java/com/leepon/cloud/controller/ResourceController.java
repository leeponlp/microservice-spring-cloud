package com.leepon.cloud.controller;

import com.leepon.cloud.constant.ConstantKey;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public void login(String username, String password, HttpServletResponse response) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails != null) {

                    List roleList = new ArrayList<>();
            String subject = userDetails.getUsername() + "-" + roleList;
            String token = Jwts.builder()
                    .setSubject(subject)
                    .setExpiration(new Date(System.currentTimeMillis() + 365 * 24 * 60 * 60 * 1000)) // 设置过期时间 365 * 24 * 60 * 60秒(这里为了方便测试，所以设置了1年的过期时间，实际项目需要根据自己的情况修改)
                    .signWith(SignatureAlgorithm.HS512, ConstantKey.SIGNING_KEY) //采用什么算法是可以自己选择的，不一定非要采用HS512
                    .compact();
            // 登录成功后，返回token到header里面
            response.addHeader("Authorization", "Bearer " + token);
        }
    }

    @RequestMapping("/hello")
    public String helloWorld(){
        return "Hello World!";
    }
}
