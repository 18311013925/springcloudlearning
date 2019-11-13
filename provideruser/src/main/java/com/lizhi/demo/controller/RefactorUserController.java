package com.lizhi.demo.controller;

import com.lizhi.demo.dao.UserRepository;
import com.lizhi.demo.dto.User;
import com.lizhi.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lizhi
 * @Date: 2019/11/12 11:49
 * @Description:
 */
@RestController
public class RefactorUserController implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserController userController;

    @Override
    public User getUserById(@PathVariable Long id) {
        User findOne = userController.findById(id);
        return findOne;
    }

}
