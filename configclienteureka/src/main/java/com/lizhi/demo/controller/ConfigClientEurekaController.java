package com.lizhi.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lizhi
 * @Date: 2019/10/31 16:13
 * @Description:
 */
@RestController
@RefreshScope
public class ConfigClientEurekaController {

    @Value("${profile}")
    private String profile;

    @GetMapping("/hello")
    public String hello(){
        return this.profile;
    }
}
