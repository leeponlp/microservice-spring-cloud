package com.leepon.cloud.feign;

import com.leepon.cloud.service.IUserService;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name = "microservice-user",fallback = OuterUserServiceFallback.class)
public interface IOuterUserService extends IUserService {

}
