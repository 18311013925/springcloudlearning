package com.lizhi.demo.feignclient;

import com.lizhi.demo.dto.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: lizhi
 * @Date: 2019/10/25 10:38
 * @Description:
 */

@FeignClient(name = "provider-user")
public interface UserFeignClient {

    @RequestMapping(value = "/{id}")
    User findByIdFeign(@RequestParam("id") Long id);
}

/**
 * 使用@FeignClient 注解的fallback属性，指定fallback 类
 * @author lizhi
 */
//@FeignClient(name = "provider-user", fallback =UserFeignClient.HystrixClientFallback.class)
//public interface UserFeignClient {
//
//    @GetMapping("/{id}")
//    User findByIdFeign( @PathVariable("id") Long id);
//
//    @Component
//    class HystrixClientFallback implements UserFeignClient {
//        private static final Logger LOGGER = LoggerFactory.getLogger(HystrixClientFallback.class);
//
//        @Override
//        public User findByIdFeign(Long id) {
//            HystrixClientFallback.LOGGER.info("异常发生，进入fallback方法，接收的参数：id = {}", id);
//            User user = new User();
//            user.setId(-1L);
//            user.setUsername("default username");
//            user.setAge(0);
//            return user;
//        }
//    }
//
//}


