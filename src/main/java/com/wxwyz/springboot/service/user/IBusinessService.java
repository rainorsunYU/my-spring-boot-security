package com.wxwyz.springboot.service.user;

import com.wxwyz.springboot.model.Business;

import java.util.List;

public interface IBusinessService {

    List<Business> queryAllBusiness();

    Business queryOneBusiness(String username);

    Integer updateAJobState(Integer jobId, Integer state);

}
