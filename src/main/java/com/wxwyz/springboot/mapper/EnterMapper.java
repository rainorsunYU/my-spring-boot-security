package com.wxwyz.springboot.mapper;

import com.wxwyz.springboot.dto.UserEnterDTO;
import com.wxwyz.springboot.dto.UserEnterJobDTO;
import com.wxwyz.springboot.model.Enter;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface EnterMapper {

    List<UserEnterDTO> queryUserEnterJob(Integer userId);

    UserEnterDTO queryAEnterJob(Integer userId, Integer jobId);

    Integer insertAEnterJob(Enter enter);

    List<UserEnterJobDTO> queryOneUserEnter(Integer offset, Integer size, Integer userId);

    Integer queryOneUserEnterNum(Integer userId);

    Integer deleteAEnterJob(Integer enterId);
}
