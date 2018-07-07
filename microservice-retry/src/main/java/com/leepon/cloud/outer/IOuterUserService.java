package com.leepon.cloud.outer;

import com.leepon.cloud.service.IUserService;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name = "microservice-user")
public interface IOuterUserService extends IUserService {




}
