package com.lizhi.demo.async.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lizhi.demo.async.AsyncBody;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * @author: lizhi
 * @Date: 2019/11/6 14:23
 * @Description:
 */
@Slf4j
public abstract class AbstractAsyncProducer {

    @Autowired
    protected Channel channel;

    @Autowired
    private ObjectMapper objectMapper;

    protected abstract String getType();

    protected abstract String getExchange();

    /**
     * exchange:
     *          topic: defaultRoutingKey
     *          direct: queue name
     * @return
     */
    protected abstract String getDefaultRoutingKey();

    protected byte[] getMessageBody(AsyncBody body) {
        if (body == null) {
            return null;
        }

        byte[] bytes = null;
        try {
            bytes = objectMapper.writeValueAsBytes(body);
        } catch (JsonProcessingException e) {
            log.error("{}", e);
        }
        return bytes;
    }

    /**
     * 发送多个持久化的消息
     * 并不一定能保证消息持久化
     * 还需要declare exchange && queue 持久化
     * @param objects
     */
    public void durabilityPublish(Object... objects) {
        for (Object body : objects) {
            durabilityPublish(body);
        }
    }

    /**
     * 发送持久化的消息
     * 并不一定能保证消息持久化
     * 还需要declare exchange && queue 持久化
     * @param body
     */
    public void durabilityPublish(Object body) {
        if (body == null) {
            log.error("{}", "wbg async producer handle empty message body");
            return;
        }
        durabilityPublish(body, getDefaultRoutingKey());
    }

    /**
     * 发送持久化的消息
     * 并不一定能保证消息持久化
     * 还需要declare exchange && queue 持久化
     *
     * @param body
     * @param routingKey
     */
    public void durabilityPublish(Object body, String routingKey) {
        if (body == null) {
            log.error("{}", "wbg async producer handle empty message body");
            return;
        }
        durabilityPublish(new AsyncBody(body), routingKey);
    }

    /**
     * 发送持久化的消息
     * 并不一定能保证消息持久化
     * 还需要declare exchange && queue 持久化
     * @param body
     * @param routingKey
     */
    public void durabilityPublish(AsyncBody body, String routingKey) {
        customPublish(body, MessageProperties.PERSISTENT_TEXT_PLAIN, routingKey);
    }


    public void customPublish(AsyncBody body, AMQP.BasicProperties messageProperty, String routingKey) {
        log.info("wbg async event message sending. body: {}, routingKey: {}", body, routingKey);
        if (messageProperty == null) {
            return;
        }

        byte[] bytes = getMessageBody(body);
        if (bytes == null) {
            return;
        }

        try {
            channel.basicPublish(
                    getExchange(),
                    routingKey,
                    messageProperty,
                    bytes
            );
        } catch (IOException e) {
            log.error("{}", e);
        } catch (Throwable t) {
            log.error("{}", t);
        }
    }

}
