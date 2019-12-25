package com.wxwyz.springboot.model;

import lombok.Data;

import java.util.Date;

@Data
public class Enter {

    private Integer enterId;
    private Integer userId;
    private Integer jobId;
    private Date enterTime;
}
