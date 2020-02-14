package com.dq.work4.service.impl;

import com.dq.work4.entity.User;
import com.dq.work4.mapper.UserMapper;
import com.dq.work4.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserMapper userMapper;
    @Override
    public User login(String username) {
        return userMapper.login(username);
    }
}
