package com.lizhi.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: lizhi
 * @Date: 2019/11/8 16:28
 * @Description:
 * @SpringBootApplication 告诉Spring boot 框架，这是项目的引导类
 */
@SpringBootApplication
public class SpringBootServerApplication {
    public static void main(String[] args) {
        //调用启动整个Spring Boot 服务
        SpringApplication.run(SpringBootServerApplication.class, args);
    }
}
