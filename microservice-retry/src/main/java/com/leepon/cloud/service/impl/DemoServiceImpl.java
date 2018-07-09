package com.leepon.cloud.service.impl;

import com.leepon.cloud.entity.User;
import com.leepon.cloud.outer.IOuterUserService;
import com.leepon.cloud.service.IDemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author admin
 * @Date 2018/7/7 下午10:54
 * @Version 1.0
 */
@Slf4j
@Service
public class DemoServiceImpl implements IDemoService {

    @Autowired
    IOuterUserService outerUserService;

    @Retryable(value= {Exception.class},maxAttempts = 5,backoff = @Backoff(delay = 1000,multiplier = 2))
    @Override
    public User findById(Integer id) {
        log.info("=====RPC调用客户中心服务=====");
        int i = 1/0;
        return outerUserService.findById(id);
    }
}
