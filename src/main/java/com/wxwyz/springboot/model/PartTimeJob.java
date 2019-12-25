package com.wxwyz.springboot.model;

import lombok.Data;

import java.util.Date;

@Data
public class PartTimeJob {

    private Integer jobId;
    private String token;//唯一token标识
    private Integer parentId;
    private String jobTitle;
    private String jobContent;
    private String jobLocation;
    private Integer jobViews;//浏览人数
    private Integer needNumber;//需要的人数
    private Integer applicants;//报名人数
    private String jobType;//工作类型
    private Double jobSalary;
    private Date releaseTime;//发布时间
    private Integer state;//状态
    private Date startTime;
    private Date endTime;

}
