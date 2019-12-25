package com.wxwyz.springboot.dto;

import com.wxwyz.springboot.model.PartTimeJob;
import lombok.Data;

@Data
public class BusinessAllInfoDTO {

    private Integer id;

    private String nickname;

    private String avatars;

    private Integer comments=0;

    private String phone;

    private PartTimeJob partTimeJob;

}
