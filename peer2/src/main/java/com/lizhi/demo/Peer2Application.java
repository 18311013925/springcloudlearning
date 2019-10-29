package com.lizhi.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author: lizhi
 * @Date: 2019/10/23 14:00
 * @Description:
 */
@SpringBootApplication
@EnableEurekaServer
public class Peer2Application {
    public static void main(String[] args) {
        SpringApplication.run(Peer2Application.class, args);
    }
}
