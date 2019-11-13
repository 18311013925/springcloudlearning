package com.lizhi.demo.controller;

import com.lizhi.demo.dto.User;
import com.lizhi.demo.feignclient.RefactorUserService;
import com.lizhi.demo.feignclient.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.PathParam;

/**
 * @author: lizhi
 * @Date: 2019/10/25 10:40
 * @Description:
 */
@RestController
public class FeignController {

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private RefactorUserService refactorUserService;

    @GetMapping("feign/{id}")
    public User findByIdFeign(@PathVariable Long id) {
        return userFeignClient.findByIdFeign(id);
    }

    @GetMapping("refactor/{id}")
    public User findUserById(@PathVariable("id") Long id ) {
        User user = refactorUserService.getUserById(id);
        return user;
    }
}
