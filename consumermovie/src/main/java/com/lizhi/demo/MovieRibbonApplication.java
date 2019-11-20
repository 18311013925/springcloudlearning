package com.lizhi.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author: lizhi
 * @Date: 2019/10/24 10:25
 * @Description:
 */
@SpringBootApplication

/**
 * 将服务注册到服务发现组件上，可以用@EnableEurekaClient代替，
 * 只是@EnableEurekaClient 是注册到Eureka 上
 * 而@EnableDiscoveryClient 可以注册到zk等服务发现组件上，
 * @EnableEurekaClient是对@EnableDiscoveryClient的一个封装的符合注解
 */
@EnableDiscoveryClient
public class MovieRibbonApplication {

    /**
     * 实例化restTemplate
     * 使用@LoadBalanced 注解开启负载均衡能力
     * @return  restTemplate  RestTemplate是Spring提供的用于访问Rest服务的客户端，RestTemplate提供了多种便捷访问远程Http服务的方法,
     * 能够大大提高客户端的编写效率。
     * 该对象会使用 Ribbon 的自动化配置， 同时通过配置 @LoadBalanced 还能够开启客户端负载均衡
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(MovieRibbonApplication.class, args);
    }
}
