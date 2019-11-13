package com.lizhi.demo.feignclient;

import com.lizhi.demo.dto.User;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: lizhi
 * @Date: 2019/11/12 18:20
 * @Description:
 *  启动会报错，
 *
 */
// 方式一
//@Component
//@RequestMapping("/fallback//refactor/api")
//public class RefactorHystrixClientFallback implements RefactorUserService{
//private static final Logger logger = LoggerFactory.getLogger(RefactorHystrixClientFallback.class);
//    @Override
//    public User getUserById(Long id) {
//        logger.info("异常发生，进入fallback方法，接收的参数：id = {}", id);
//        User user = new User();
//        user.setId(-1L);
//        user.setUsername("default username");
//        user.setAge(0);
//        return user;
//    }
// }
//    方式二
@Component
public class RefactorHystrixClientFallback implements FallbackFactory<RefactorUserService>{
    private static final Logger logger = LoggerFactory.getLogger(RefactorHystrixClientFallback.class);

    @Override
    public RefactorUserService create(Throwable cause) {
        return new RefactorUserService() {
            @Override
            public User getUserById(Long id) {
                logger.info("异常发生，进入fallback方法，接收的参数：id = {}", id);
                User user = new User();
                user.setId(-1L);
                user.setUsername("default username");
                user.setAge(0);
                return user;
            }
        };
    }


}
