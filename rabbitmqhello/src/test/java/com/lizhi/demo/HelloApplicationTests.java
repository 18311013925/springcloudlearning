package com.lizhi.demo;

import com.lizhi.demo.businessAsync.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: lizhi
 * @Date: 2019/11/6 15:04
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RabbitMqHelloApplication.class})
public class HelloApplicationTests {

    @Autowired
    private Sender sender;

    @Test
    public void hello() throws Exception{
        sender.send();
    }

}
