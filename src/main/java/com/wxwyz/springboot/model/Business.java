package com.wxwyz.springboot.model;

import lombok.Data;

import java.util.Date;

@Data
public class Business {

    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String phone;
    private Date createTime;
    private String avatars;

}
