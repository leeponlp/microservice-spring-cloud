package com.leepon.cloud.feign;

import com.leepon.cloud.entity.User;
import org.springframework.stereotype.Component;


/**
 * @author leepon
 */
@Component
public class UserFeignClientFallback implements UserFeignClient {

  @Override
  public User findById(Integer id) {
    User user = new User();
    user.setId(0);
    return user;
  }

}