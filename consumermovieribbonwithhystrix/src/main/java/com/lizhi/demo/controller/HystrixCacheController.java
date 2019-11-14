package com.lizhi.demo.controller;

import com.lizhi.demo.entity.User;
import com.lizhi.demo.service.RequestCacheService;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lizhi
 * @Date: 2019/11/14 11:30
 * @Description:  每次从浏览器发起一个请求，还是会请求服务提供者，但是，在同一个方法中的多次调用是只请求一次服务提供者，
 * 这个问题也不是很明白,
 */
@RestController
public class HystrixCacheController {

    @Autowired
    private RequestCacheService cacheService;
    @GetMapping("cache/{id}")
    public User findUserById(@PathVariable Long id) {
        HystrixRequestContext.initializeContext();
        User user1 = cacheService.findById(id);
        System.out.println("用户1"+ user1.toString());
        User user2 = cacheService.findById(id);
        System.out.println("用户2"+ user2.toString());
        User user3 = cacheService.findById(id);
        System.out.println("用户3"+ user3.toString());
        return cacheService.findById(id);
    }

    /**
     * 注解方式
     * @param id
     * @return
     */
    @GetMapping("cache2/{id}")
    public User findUserById2(@PathVariable Long id) {
        HystrixRequestContext.initializeContext();
        return cacheService.findById2(id);
    }


    /**
     * 这个报错，可能使用自定义的缓存的key 不行吧，不搞了，浪费时间，遇见问题再说吧，哎
     * @param id
     * @return
     */
    @GetMapping("remove/cache/{id}")
    public String removeCacheUserById(@PathVariable Long id) {
        HystrixRequestContext.initializeContext();
        cacheService.clearCacheById(id);
        return "ok";
    }


}
