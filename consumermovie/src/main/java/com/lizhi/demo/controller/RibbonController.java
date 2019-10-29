package com.lizhi.demo.controller;

import com.lizhi.demo.entity.User;
import com.lizhi.demo.service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lizhi
 * @Date: 2019/10/24 18:03
 * @Description:
 */
@RestController
public class RibbonController {
    @Autowired
    private RibbonService ribbonService;

    @GetMapping("ribbon/{id}")
    private User findById(@PathVariable Long id) {
        return this.ribbonService.findById(id);
    }
}
