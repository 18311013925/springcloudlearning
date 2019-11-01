package com.lizhi.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author: lizhi
 * @Date: 2019/10/31 10:52
 * @Description:
 */
@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient  //注册到注册中心
public class ConfigServerEurekaAppliCation {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerEurekaAppliCation.class, args);
    }
}
