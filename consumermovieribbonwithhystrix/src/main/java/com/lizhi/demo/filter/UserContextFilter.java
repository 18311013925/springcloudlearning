package com.lizhi.demo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author: lizhi
 * @Date: 2019/11/18 11:05
 * @Description:
 */
@Component
public class UserContextFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserContextFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


        try {
            try {
                HttpServletRequest httpServletRequest = (HttpServletRequest) request;
                UserContextHolder.getUserContext()
                        .setCorrelationId(httpServletRequest.getHeader(UserContext.CORRELATION_ID))
                        .setUserToken(httpServletRequest.getHeader(UserContext.USER_TOKEN))
                        .setUserId(httpServletRequest.getHeader(UserContext.USER_ID));
                LOGGER.info("filter set userInfo success");

            } catch (Exception e) {
                LOGGER.error("filter set userContext error");
            }
            chain.doFilter(request, response);
        } finally {
            UserContextHolder.clean();
        }

    }

    @Override
    public void destroy() {

    }
}
