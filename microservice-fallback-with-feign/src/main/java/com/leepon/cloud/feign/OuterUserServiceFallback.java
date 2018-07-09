//package com.leepon.cloud.feign;
//
//import com.leepon.cloud.entity.User;
//import org.springframework.stereotype.Component;
//import java.util.ArrayList;
//import java.util.List;
//
//
///**
// * @author leepon
// */
//@Component
//public class OuterUserServiceFallback implements IOuterUserService {
//
//  @Override
//  public User findById(Integer id) {
//    User user = new User();
//    user.setId(0);
//    return user;
//  }
//
//  @Override
//  public List<User> findUserAll() {
//    List<User> users = new ArrayList<>();
//    User user = new User();
//    user.setId(0);
//    users.add(user);
//    return users;
//  }
//
//}