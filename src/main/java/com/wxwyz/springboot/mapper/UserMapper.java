package com.wxwyz.springboot.mapper;

import com.wxwyz.springboot.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {

    List<User> queryAllUser();

    User queryOneUser(String username);

}
