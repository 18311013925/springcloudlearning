package com.lizhi.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lizhi
 * @Date: 2019/10/30 15:24
 * @Description:
 * 注意 @RefreshScope 注解不能少，否则即使调用 /refresh ， 配置也不会刷新
 */
@RestController
@RefreshScope
public class ConfigClientController {

    @Value("${profile}")
    private String profile;
    @Value("${jdbc.driverClassName}")
    private String driverClassName;

    @GetMapping("/hello")
    public String hello() {
        return this.profile + this.driverClassName;
    }

}

