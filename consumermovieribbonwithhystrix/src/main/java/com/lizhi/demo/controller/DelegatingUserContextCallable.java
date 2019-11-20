package com.lizhi.demo.controller;

import com.lizhi.demo.filter.UserContext;
import com.lizhi.demo.filter.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.concurrent.Callable;

/**
 * @author: lizhi
 * @Date: 2019/11/18 14:25
 * @Description:
 */
public class DelegatingUserContextCallable<V> implements Callable<V> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DelegatingUserContextCallable.class);

    private final Callable<V> delegate;

    private UserContext originalUsrContext;

    public DelegatingUserContextCallable(Callable<V> callable, UserContext userContext) {
        Assert.notNull(callable, "delegate cannot be null");
        Assert.notNull(userContext, "userContext cannot be null");

        this.delegate = callable;
        this.originalUsrContext = userContext;
    }

    public DelegatingUserContextCallable(Callable<V> callable) {
        this(callable, UserContextHolder.getUserContext());
    }

    @Override
    public V call() throws Exception {
        UserContextHolder.setUserContext(originalUsrContext);
        try {
            return delegate.call();
        }finally {
            this.originalUsrContext = null;
        }
    }

    public static <V> Callable<V> create(Callable<V> delegate, UserContext userContext) {
        return new DelegatingUserContextCallable<V>(delegate, userContext);
    }
}
