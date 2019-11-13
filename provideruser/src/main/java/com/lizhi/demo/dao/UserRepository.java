package com.lizhi.demo.dao;

import com.lizhi.demo.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: lizhi
 * @Date: 2019/10/24 10:55
 * @Description:
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
