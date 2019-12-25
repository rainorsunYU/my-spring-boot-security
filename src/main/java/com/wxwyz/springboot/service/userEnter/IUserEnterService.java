package com.wxwyz.springboot.service.userEnter;

import com.wxwyz.springboot.dto.EnterPageDTO;
import com.wxwyz.springboot.dto.UserEnterDTO;
import com.wxwyz.springboot.model.Enter;

import java.util.List;

public interface IUserEnterService{

    List<UserEnterDTO> queryAllUserEnterJob(Integer uid);

    UserEnterDTO queryAUserEnterJob(Integer uid, Integer jid);

    Integer insertAUserEnter(Enter enter);

    EnterPageDTO queryOneUserEnter(Integer page, Integer size, Integer uid);

    Integer deleteAEnterJob(Integer enter);
}
