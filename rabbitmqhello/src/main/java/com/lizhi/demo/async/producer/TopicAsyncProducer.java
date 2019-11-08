package com.lizhi.demo.async.producer;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * @author: lizhi
 * @Date: 2019/11/6 14:36
 * @Description:
 */
@Slf4j
public abstract class TopicAsyncProducer extends AbstractAsyncProducer {
    @PostConstruct
    public final void init() {
        try {
            channel.exchangeDeclare(getExchange(), getType(), true, false, null);
        } catch (IOException e) {
            log.error("{}", e);
        } catch (Throwable t) {
            log.error("{}", t);
        }
    }

    @Override
    protected String getType() {
        return "topic";
    }

    @Override
    protected String getExchange() {
        return "topic.async";
    }
}
