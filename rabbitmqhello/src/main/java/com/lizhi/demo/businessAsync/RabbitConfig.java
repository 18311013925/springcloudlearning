package com.lizhi.demo.businessAsync;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: lizhi
 * @Date: 2019/11/6 15:00
 * @Description:
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue helloQueye() {
        return new Queue("hello");
    }
}
