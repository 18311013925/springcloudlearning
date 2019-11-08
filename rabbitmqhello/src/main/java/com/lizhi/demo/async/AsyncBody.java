package com.lizhi.demo.async;

import lombok.Getter;

import java.io.Serializable;

/**
 * @author: lizhi
 * @Date: 2019/11/6 14:25
 * @Description:
 */
public class AsyncBody implements Serializable {

    /**
     * 该class必须implements序列化, 必须有默认的构造方法
     */
    @Getter
    private Object object;

    @Getter
    private Class<?> event;

    private AsyncBody() {}

    public AsyncBody(Object _object) {
        object = _object;
        event = _object.getClass();
    }
}