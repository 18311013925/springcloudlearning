package com.lizhi.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author: lizhi
 * @Date: 2019/10/28 10:28
 * @Description:
 *
 * 测试步骤：
 * 1、访问http:localhost:8090/hystrix.stream 可以查看Dashboard;
 * 2、在上面的输入框填入：http://想监控的服务:端口/hystrix.stream进行测试
 * 注意：首先要先调用一下想监控的服务的API,否则将会显示一个空的图表，
 *
 */
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardApplication.class, args);
    }
}
