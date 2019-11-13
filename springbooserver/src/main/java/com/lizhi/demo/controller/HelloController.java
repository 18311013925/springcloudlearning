package com.lizhi.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lizhi
 * @Date: 2019/11/8 16:30
 * @Description:
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {

        return "hello";
    }

}
