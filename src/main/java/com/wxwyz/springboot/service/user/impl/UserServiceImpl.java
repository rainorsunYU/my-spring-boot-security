package com.wxwyz.springboot.service.user.impl;

import com.wxwyz.springboot.mapper.UserMapper;
import com.wxwyz.springboot.model.User;
import com.wxwyz.springboot.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryOneUser(String username) {

        return userMapper.queryOneUser(username);

    }
}
