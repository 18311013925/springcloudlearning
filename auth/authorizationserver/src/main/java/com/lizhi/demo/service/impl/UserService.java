package com.lizhi.demo.service.impl;

import com.lizhi.demo.provider.OrganizationProvider;
import com.lizhi.demo.service.IUserService;

import com.lizhi.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private OrganizationProvider organizationProvider;

    @Override
    public User getByUniqueId(String uniqueId) {
        return organizationProvider.getUserByUniqueId(uniqueId).getData();
    }
}
