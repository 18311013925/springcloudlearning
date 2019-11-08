package com.lizhi.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author: lizhi
 * @Date: 2019/11/7 10:24
 * @Description:
 */
@SpringBootApplication
@EnableResourceServer       //资源服务器
@EnableAuthorizationServer  //用于告诉SpringCloud，该服务将作为OAuth2服务
public class AuthenServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthenServiceApplication.class, args);
    }
}
