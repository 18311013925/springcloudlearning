package com.lizhi.demo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: lizhi
 * @Date: 2019/11/6 11:09
 * @Description:
 */
//@Component
public class ErrorFilter extends ZuulFilter{
    private static final Logger log = LoggerFactory.getLogger(ErrorFilter.class);

    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        Throwable throwable = ctx.getThrowable();

        log.error("this is a ErrorFilter : {)", throwable.getCause().getMessage()); ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR); ctx.set("error.exception", throwable.getCause());
        return null;
    }
}
