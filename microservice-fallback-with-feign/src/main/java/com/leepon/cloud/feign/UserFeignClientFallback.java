package com.leepon.cloud.feign;

import com.leepon.cloud.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author admin
 * @Date 2018/7/9 上午11:06
 * @Version 1.0
 */
@Slf4j
@Component
public class UserFeignClientFallback implements UserFeignClient{
    @Override
    public User findById(Integer id) {
        User user = new User();
        user.setId(0);
        return user;
    }
}
