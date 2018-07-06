package com.leepon.cloud.feign;

import com.leepon.cloud.entity.User;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description TODO
 * @Author admin
 * @Date 2018/7/6 上午11:55
 * @Version 1.0
 */
@Slf4j
@Component
public class UserFeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {

    @Override
    public UserFeignClient create(Throwable throwable) {
        log.error("服务调用失败：{}",throwable.getStackTrace());
        return id -> {
            User user = new User();
            user.setId(0);
            return user;
        };
    }
}
