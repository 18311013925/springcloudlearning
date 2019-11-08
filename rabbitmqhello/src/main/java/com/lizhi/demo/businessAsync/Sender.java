package com.lizhi.demo.businessAsync;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author: lizhi
 * @Date: 2019/11/6 14:50
 * @Description:
 * 通过注入AmqpTemplate 接口的实例来实现消息的发 送AmqpTemplate接口定义了一套针对AMQP协议的基础操作
 */
@Component
public class Sender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hello " + new Date();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }
}
