package com.lizhi.demo.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.util.RequestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: lizhi
 * @Date: 2019/11/1 14:50
 * @Description:  通过前置网关实现客户端请求的安全校验和权限控制
 * 我们需要继承ZullFilter 抽象类，并实现它定义的四个抽象函数就可以完成对请求的拦截和过滤
 *
 * 相当于一个前置过滤器
 */
public class AccessFilter extends ZuulFilter{
    private static final Logger log = LoggerFactory.getLogger(AccessFilter.class);

    /**
     * flterType: 过滤器的类型， 它决定过滤器在请求的哪个生命周期中执行。 这里 定义为pre, 代表会在请求被路由之前执行。
     * 1、pre: 可以在请求被路由之前调用。
     * 2、routing: 在路由请求时被调用。
     * 3、post: 在 routing 和 error 过滤器之后被调用。
     * 4、error: 处理请求时发生错误时被调用。
     * @return
     */
    @Override
    public String filterType() {
        log.info("请求前: pre");
        return "pre";
    }

    /**
     * flterOrder: 过滤器的执行顺序。 当请求在 一个阶段中存在多个过滤器时， 需 要根据该方法返回的值来依次执行。
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * shouldF辽ter: 判断该过滤器是否需要被执行。 这里我们直接返回了true, 因 此该过滤器对所有请求都会生效。 实际运用中我们可以利用该函数来指定过滤器的
     有效范围。
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * run: 过滤器的具体逻辑。 这里我们通过ctx.setSendZuulResponse(false) 令zuul过滤该请求， 不对其进行路由， 然后通过 ctx.setResponseStatus­ Code(401)设置了其返回的错误码， 当然也可以进 一 步优化我们的返回， 比如， 通 过ctx.se七ResponseBody(body)对返回的body内容进行编辑等。
     *
     *
     * @return
     */
    @Override
    public Object run() {
        /**
         *  {@link org.springframework.cloud.netflix.zuul.filters.pre.ServletDetectionFilter#filterOrder()}
         *  ServletDetectionFilter:主要用来检测当前请求是通过Spring的DispatcherServlet 处理运行的，还是通过Zuu1Servle七来处理运行的
         */
        boolean dispatcherServletRequest = RequestUtils.isDispatcherServletRequest();
        boolean zuulServletRequest = RequestUtils.isZuulServletRequest();

        log.info("dispatcherServletRequest:{} zuulServletRequest {}", dispatcherServletRequest, zuulServletRequest);

        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        log.info("send {} request to {} ", request.getMethod(), request.getRequestURL().toString());

        String accessToken = request.getParameter("accessToken");
        if (accessToken == null) {
            log.warn("access token is empty");
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseStatusCode(401);
            currentContext.setResponseBody(returnError("error", 401, null));
            return null;
        }
        log.info("access token ok ");
        return null;
    }

    public String returnError(String message, Integer code, Object data) {
        Map<String, Object> map = new HashMap();
        map.put("message", message);
        map.put("code", 401);
        map.put("data", data);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}


