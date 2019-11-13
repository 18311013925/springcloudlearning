package com.lizhi.demo.service;

import com.lizhi.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author: lizhi
 * @Date: 2019/10/24 17:56
 * @Description:
 */
@Service
public class RibbonService {

    @Autowired
    private RestTemplate restTemplate ;

    public User findById(Long id) {
        return this.restTemplate.getForObject("http://provider-user/" + id, User.class);
    }

    public List<User> queryUserList() {
        return this.restTemplate.getForObject("http://provider-user/query/users", List.class);
    }
}
