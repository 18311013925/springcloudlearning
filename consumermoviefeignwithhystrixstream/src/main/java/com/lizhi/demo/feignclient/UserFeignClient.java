package com.lizhi.demo.feignclient;

//import com.lizhi.demo.entity.User;
import com.lizhi.demo.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 使用@FeignClient 注解的fallback属性，指定fallback 类， fallback 实现服务降级
 * @author lizhi
 */
@FeignClient(name = "provider-user", fallback =UserFeignClient.HystrixClientFallback.class)
public interface UserFeignClient {

    @GetMapping("/{id}")
    User findByIdFeign(@PathVariable("id") Long id);

    @Component
    class HystrixClientFallback implements UserFeignClient {
        private static final Logger LOGGER = LoggerFactory.getLogger(HystrixClientFallback.class);

        @Override
        public User findByIdFeign(Long id) {
            HystrixClientFallback.LOGGER.info("异常发生，进入fallback方法，接收的参数：id = {}", id);
            User user = new User();
            user.setId(-1L);
            user.setUsername("default username");
            user.setAge(0);
            return user;
        }
    }

}


