package com.lizhi.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @author: lizhi
 * @Date: 2019/10/25 10:33
 * @Description:
 * 使用 @EnableFeignClients 开启Feign
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class MovieFeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(MovieFeignApplication.class, args);
    }
}
