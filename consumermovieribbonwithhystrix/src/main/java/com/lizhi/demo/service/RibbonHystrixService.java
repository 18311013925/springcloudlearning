package com.lizhi.demo.service;

import com.lizhi.demo.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author: lizhi
 * @Date: 2019/10/25 16:09
 * @Description:
 */
@Service
public class RibbonHystrixService {

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(RibbonHystrixService.class);

    /**
     * 使用@HystrixCommand 注解指定当该方法发生异常时调用的方法
     * @param id
     * @return 通过id查询的用户
     */
    @HystrixCommand(fallbackMethod = "fallback")
    public User findById(Long id) {
        return this.restTemplate.getForObject("http://provider-user/" + id, User.class);
    }

    /**
     * hystrix fallback 方法
     * @param id
     * @return
     */
    public User fallback(Long id) {
        RibbonHystrixService.LOGGER.info("异常发生，进入fallback方法，接收的参数：id = {}", id);
        User user = new User();
        user.setId(-1L);
        user.setUsername("default username");
        user.setAge(0);
        return user;
    }
}
