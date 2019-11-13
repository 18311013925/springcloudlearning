package com.lizhi.demo.service;

import com.lizhi.demo.dto.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lizhi
 * @Date: 2019/11/12 11:40
 * @Description:
 */
@RestController("/refactor/api")
public interface UserService {

    @GetMapping("/getUser/{id}")
    User getUserById(@PathVariable("id") Long id);
}
