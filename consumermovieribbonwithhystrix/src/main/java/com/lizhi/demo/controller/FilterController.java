package com.lizhi.demo.controller;

import com.lizhi.demo.filter.UserContext;
import com.lizhi.demo.filter.UserContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lizhi
 * @Date: 2019/11/18 11:49
 * @Description: 测试Filter 在请求中携带 user_id , user_token
 * 访问地址
 * curl http://localhost:8088/userFilter/v1.json -H 'user_id:8898' -H'user_token:sdkflsdjflaskfjalkfjlasjdfiour4823049'
 *
 */
@RestController
public class FilterController {

    @GetMapping("userFilter/v1")
    public UserContext userFilter() {
        return UserContextHolder.getUserContext();
    }
}
