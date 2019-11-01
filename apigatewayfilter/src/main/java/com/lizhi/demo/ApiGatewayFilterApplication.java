package com.lizhi.demo;

import com.lizhi.demo.filter.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

/**
 * @author: lizhi
 * @Date: 2019/11/1 15:22
 * @Description:
 */
@SpringBootApplication
@EnableZuulProxy
public class ApiGatewayFilterApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayFilterApplication.class, args);
    }

    @Bean
    public AccessFilter accessFilter() {
        return new AccessFilter();
    }
}
