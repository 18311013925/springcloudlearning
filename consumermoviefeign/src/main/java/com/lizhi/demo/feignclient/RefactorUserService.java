package com.lizhi.demo.feignclient;

import com.lizhi.demo.service.UserService;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * @author: lizhi
 * @Date: 2019/11/12 14:27
 * @Description:  这个类是为了测试feign 的继承特性，
 *
 */

@FeignClient(name = "provider-user")
public interface RefactorUserService extends UserService {
}
