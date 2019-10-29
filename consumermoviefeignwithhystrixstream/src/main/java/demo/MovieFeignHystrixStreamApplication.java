package demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
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
@EnableCircuitBreaker
public class MovieFeignHystrixStreamApplication {
    public static void main(String[] args) {
        SpringApplication.run(MovieFeignHystrixStreamApplication.class, args);
    }
}
