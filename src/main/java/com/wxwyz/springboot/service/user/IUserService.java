package com.wxwyz.springboot.service.user;

import com.wxwyz.springboot.model.User;

public interface IUserService {

    User queryOneUser(String username);

}
