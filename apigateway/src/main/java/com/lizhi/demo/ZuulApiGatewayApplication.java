package com.lizhi.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author: lizhi
 * @Date: 2019/11/1 10:56
 * @Description:
 * 使用@EnableZuulProxy 注解激活zuul
 * 跟进该注解可以看到该注解整合了 @EnableCircuitBreaker ，是个组合注解，目的是简化配置
 *
 */
@SpringBootApplication
@EnableZuulProxy
public class ZuulApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApiGatewayApplication.class, args);
    }

}
