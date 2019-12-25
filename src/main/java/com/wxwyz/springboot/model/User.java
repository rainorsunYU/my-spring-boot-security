package com.wxwyz.springboot.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Integer id;
    private String username;
    private String password;
    private String realName;
    private String nickname;
    private String phone;
    private Date createTime;
    private String avatars;

}
