package com.wxwyz.springboot.model;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {

    private Integer commentId;
    private Integer jobId;
    private String commentator;
    private String avatar;
    private String content;
    private Date createTime;

}
