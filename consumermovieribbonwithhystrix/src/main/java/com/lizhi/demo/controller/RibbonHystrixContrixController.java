package com.lizhi.demo.controller;

import com.lizhi.demo.entity.User;
import com.lizhi.demo.service.RibbonHystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lizhi
 * @Date: 2019/10/25 10:40
 * @Description:
 */
@RestController
public class RibbonHystrixContrixController {

    @Autowired
    private RibbonHystrixService ribbonHystrixService;

    @GetMapping("ribbon/{id}")
    public User findByIdFeign(@PathVariable Long id) {
        return ribbonHystrixService.findById(id);
    }
}
