package com.leepon.cloud.feign;

import com.leepon.cloud.entity.User;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author admin
 * @Date 2018/7/6 上午11:55
 * @Version 1.0
 */
@Slf4j
@Component
public class OuterUserServiceFallbackFactory implements FallbackFactory<IOuterUserService> {

    @Override
    public IOuterUserService create(Throwable throwable) {
        log.error("服务调用失败：{}",throwable);
        return new IOuterUserService() {
            @Override
            public User findById(Integer id) {
                User user = new User();
                user.setId(1);
                user.setUsername("苏小城");
                user.setAge(28);
                return user;
            }

            @Override
            public List<User> findUserAll() {
                List<User> users = new ArrayList<>();
                User user = new User();
                user.setId(1);
                user.setUsername("苏小城");
                user.setAge(28);
                users.add(user);
                return users;
            }
        };
    }
}
