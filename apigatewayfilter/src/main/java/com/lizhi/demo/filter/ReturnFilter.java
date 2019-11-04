package com.lizhi.demo.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lizhi.Result;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Map;

/**
 * @author: lizhi
 * @Date: 2019/11/4 11:10
 * @Description:  请求返回的Filter
 */
public class ReturnFilter extends ZuulFilter {
    private static final Logger log = LoggerFactory.getLogger(ReturnFilter.class);
    @Override
    public String filterType() {
        log.info("请求完成返回： post");
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext currentContext = RequestContext.getCurrentContext();
        Boolean filterResult = (Boolean) currentContext.get("isSuccess");
        return true;
    }

    /**
     * 对返回的结果进行包装
     * @return
     */
    @Override
    public Object run() {
        log.info("post ruturn");
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletResponse response = context.getResponse();
        int status = response.getStatus();
        InputStream dataStream = context.getResponseDataStream();
        try {
            String body = StreamUtils.copyToString(dataStream, Charset.forName("UTF-8"));
            ObjectMapper objectMapper = new ObjectMapper();
            Object object = objectMapper.readValue(body, Object.class);
            Result result = new Result();
            result.setStatus(status);
            if (status == HttpStatus.OK.value()) {
                result.setMessage("ok");
            } else {
                result.setMessage("error");
            }
            result.setData(object);
            context.setResponseBody(objectMapper.writeValueAsString(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
