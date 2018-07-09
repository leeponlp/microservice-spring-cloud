package com.leepon.cloud.feign;

import com.leepon.cloud.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description TODO
 * @Author admin
 * @Date 2018/7/9 上午11:05
 * @Version 1.0
 */
@FeignClient(name = "microservice-user",fallback = UserFeignClientFallback.class)
public interface UserFeignClient {

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    User findById(@PathVariable(value = "id") Integer id);
}
