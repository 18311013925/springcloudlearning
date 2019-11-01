package com.lizhi.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author: lizhi
 * @Date: 2019/10/30 14:23
 * @Description:
 *
 * 通过 @EnableConfigServer 注解激活配置服务
 * 说明：
 * 在application 中有个git.url 的配置，目前配置的是
 */

@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
