package com.lizhi.demo.async.producer;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: lizhi
 * @Date: 2019/11/6 14:44
 * @Description:
 */
@Slf4j
public abstract class DirectAsyncProducer extends AbstractAsyncProducer  {
    @Override
    protected String getType() {
        return "direct";
    }

    @Override
    protected String getExchange() {
        return "direct.async";
    }

}
