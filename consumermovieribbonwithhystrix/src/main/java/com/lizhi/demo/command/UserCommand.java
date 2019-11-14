package com.lizhi.demo.command;

import com.lizhi.demo.entity.User;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * @author: lizhi
 * @Date: 2019/11/13 15:18
 * @Description:
 */
public class UserCommand extends HystrixCommand<User>{

    private static final Logger logger = LoggerFactory.getLogger(UserCommand.class);

    @Autowired
    private RestTemplate restTemplate;

    private Long id;

    public UserCommand(RestTemplate restTemplate, Long id) {
        super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
        this.restTemplate = restTemplate;
        this.id = id;
    }

    @Override
    protected User run() throws Exception {
        return restTemplate.getForObject("http://provider-user/{1}", User.class, id);
    }

    /**
     * 调用失败后做的事情
     * @return
     */
    @Override
    protected User getFallback() {
//        return super.getFallback();
        logger.info("异常发生，进入fallback方法，接收的参数：id = {}", id);
        User user = new User();
        user.setId(-1L);
        user.setUsername("default username");
        user.setAge(0);
        return user;
    }

    @Override
    protected String getCacheKey() {
        return super.getCacheKey();
    }
}
