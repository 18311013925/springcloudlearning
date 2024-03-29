package com.lizhi.demo.controller;

//import com.lizhi.demo.entity.User;
import com.lizhi.demo.dto.User;
import com.lizhi.demo.feignclient.RefactorUserService;
import com.lizhi.demo.feignclient.UserFeignClient;
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
public class FeignController {

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private RefactorUserService refactorUserService;

    @GetMapping("feign/{id}")
    public User findByIdFeign(@PathVariable Long id) {
        return userFeignClient.findByIdFeign(id);
    }

    /**
     * 重构Api 将API 服务抽出来
     * @param id
     * @return
     */
    @GetMapping("/refactor/{id}")
    public User findUserByIdFeign(@PathVariable Long id) {
        User user = refactorUserService.getUserById(id);

        return user;
    }
}
