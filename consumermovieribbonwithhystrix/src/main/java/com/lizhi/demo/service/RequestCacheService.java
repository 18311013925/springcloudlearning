package com.lizhi.demo.service;

import com.lizhi.demo.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author: lizhi
 * @Date: 2019/11/14 10:58
 * @Description: hystrix 请求缓存,
 */
@Service
public class RequestCacheService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestCacheService.class);

    private static final String CACHE_KEY = "user_";
    private static final String GROUP_KEY = "userCommond";
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallback")
    //将请求结果放到缓存里, cacheKeyMethod 是生成key的方法， 如果不指定，就使用参数作为key
    @CacheResult(cacheKeyMethod = "getUserByIdCacheKey")
    public User findById(Long id) {
        return this.restTemplate.getForObject("http://provider-user/{1}", User.class, id);
    }

    private String getUserByIdCacheKey(Long id) {
        LOGGER.info("生成缓存：{}", CACHE_KEY+id);
        return CACHE_KEY + id;
    }

    /**
     * 使用@CacheKey 来标明缓存key
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallback")
    public User findById2(@CacheKey(CACHE_KEY+"id") Long id) {
        return this.restTemplate.getForObject("http://provider-user/{1}", User.class, id);
    }

    @CacheRemove(commandKey = "findById")
    @HystrixCommand
    public void clearCacheById(@CacheKey(CACHE_KEY+"id") Long id) {
        LOGGER.info("移除缓存：{}", id);
    }


    public User fallback(Long id, Throwable e) {
        LOGGER.info("异常发生，进入fallback方法，接收的参数：id = {}", id);
        LOGGER.info("fallback exception：{}", e.getMessage());
        User user = new User();
        user.setId(-1L);
        user.setUsername("default username");
        user.setAge(0);
        return user;
    }
}
