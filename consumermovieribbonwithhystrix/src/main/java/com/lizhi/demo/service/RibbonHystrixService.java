package com.lizhi.demo.service;

import com.lizhi.demo.command.UserCommand;
import com.lizhi.demo.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author: lizhi
 * @Date: 2019/10/25 16:09
 * @Description:
 */
@Service
public class RibbonHystrixService {

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(RibbonHystrixService.class);

    /**
     * 使用@HystrixCommand 注解指定当该方法发生异常时调用的方法
     *  同步执行
     * @param id
     * @return 通过id查询的用户
     */
    @HystrixCommand(fallbackMethod = "fallback")
    public User findById(Long id) {
        return this.restTemplate.getForObject("http://provider-user/{1}", User.class, id);
    }

    /**
     * hystrix fallback 方法
     *
     * @param id
     * @return
     */
    public User fallback(Long id, Throwable e) {
        LOGGER.info("异常发生，进入fallback方法，接收的参数：id = {}", id);
        LOGGER.info("fallback exception：{}", e.getMessage());
        User user = new User();
        user.setId(-1L);
        user.setUsername("default username");
        user.setAge(0);
        return user;
    }

    /**
     * 异步执行
     */

    @HystrixCommand(fallbackMethod = "fallback")
    public Future<User> findByIdAsync(final Long id) {
        AsyncResult<User> asyncResult = new AsyncResult<User>() {
            @Override
            public User invoke() {
                return restTemplate.getForObject("http://provider-user/{1}", User.class, id);
            }
        };

        return asyncResult;
    }


    /** =========================  ******通过继承方式 HystrixCommand ******  ================= */
    // 一、继承方式实现HystrixCommand

    /**
     * HystrixCommand 高级配置，同步
     *
     * @param id
     * @return
     */
    public User syncystrixfindById(Long id) {
        User user = new UserCommand(restTemplate, id).execute();
        return user;
    }

    /**
     * 异步
     *
     * @param id
     * @return
     */
    public User asyncHystrixfindById(Long id) throws ExecutionException, InterruptedException {
        Future<User> queue = new UserCommand(restTemplate, id).queue();
        return queue.get();
    }

    /**
     * 虽然HystrixCommand具备了observe()和toObservable()的功能，但是它的实现有一定的局限性，
     * 它返回的Observable只能发射一次数据，所以Hystrix还提供了HystrixObservableCommand,
     * 通过它实现的命令可以获取能发多次的Observable
     */

    public User observeHystrixfindById(Long id) throws ExecutionException, InterruptedException {
        /**
         * 返回的是Hot Observable,HotObservable，不论 “事件源” 是否有“订阅者”
         * 都会在创建后对事件进行发布。所以对于Hot Observable的每一个“订阅者”都有
         * 可能从“事件源”的中途开始的，并可能只是看到了整个操作的局部过程
         */
        Observable<User> observe = new UserCommand(restTemplate, id).observe();
        User user = observe.toBlocking().single();
        LOGGER.info("observe: {}", user.toString());
        observe.subscribe(new Observer<User>() {
            @Override
            public void onCompleted() {
                LOGGER.info("没有完成：{}", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                LOGGER.info("发成错误:{} ****  {}", "onError", e);
            }

            @Override
            public void onNext(User user) {
                LOGGER.info("执行下一个", user);
            }
        });

        observe.subscribe(new Action1<User>() {
            @Override
            public void call(User user) {
                LOGGER.info("回调：{}", user);
            }
        });
        return user;
    }

    /**
     * toObservable
     * @param id
     * @return
     */
    public User toObservable(Long id) {
        /**
         * Cold Observable在没有 “订阅者” 的时候并不会发布时间，
         * 而是进行等待，知道有 “订阅者” 之后才发布事件，所以对于
         * Cold Observable的订阅者，它可以保证从一开始看到整个操作的全部过程。
         */
        Observable<User> userObservable = new UserCommand(restTemplate, id).toObservable();
        User user = userObservable.toBlocking().single();
        LOGGER.info("toObservable:{}",user.toString() );
        return user;


    }

}
