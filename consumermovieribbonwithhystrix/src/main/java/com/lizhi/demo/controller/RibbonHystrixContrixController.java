package com.lizhi.demo.controller;

import com.lizhi.demo.entity.User;
import com.lizhi.demo.service.RibbonHystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author: lizhi
 * @Date: 2019/10/25 10:40
 * @Description:
 */
@RestController
public class RibbonHystrixContrixController {

    @Autowired
    private RibbonHystrixService ribbonHystrixService;

    /**
     * 注解方式
     * @param id
     * @return
     */
    @GetMapping("ribbon/{id}")
    public User findByIdFeign(@PathVariable Long id) {
        return ribbonHystrixService.findById(id);
    }


    /**
     * 注解方式异步调用
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("anno/ribbon/{id}")
    public User findByIdFeignAnnoAsync(@PathVariable Long id) throws Exception {
        Future<User> future = ribbonHystrixService.findByIdAsync(id);
        User user = future.get();
        return user;
    }



    /**
     * hystrixCommand 高级配值，继承方式1
     * @param id
     * @return
     */
    @GetMapping("sync/{id}")
    public User findByIdFeignSync(@PathVariable Long id) {
        return ribbonHystrixService.syncystrixfindById(id);
    }

    /**
     * hystrixCommand 高级配值，异步继承方式
     * @param id
     * @return
     */
    @GetMapping("async/{id}")
    public User findByIdFeignAsync(@PathVariable Long id) throws ExecutionException, InterruptedException {
        return ribbonHystrixService.asyncHystrixfindById(id);
    }

    /**
     * observer
     * @param id
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */

    @GetMapping("observer/{id}")
    public User findByIdFeignObserver(@PathVariable Long id) throws ExecutionException, InterruptedException {
        return ribbonHystrixService.observeHystrixfindById(id);
    }

    @GetMapping("to/observer/{id}")
    public User findByIdFeignTOObserver(@PathVariable Long id) throws ExecutionException, InterruptedException {
        return ribbonHystrixService.toObservable(id);
    }





}
