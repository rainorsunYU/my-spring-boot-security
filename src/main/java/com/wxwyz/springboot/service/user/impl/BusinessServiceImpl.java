package com.wxwyz.springboot.service.user.impl;

import com.wxwyz.springboot.mapper.BusinessMapper;
import com.wxwyz.springboot.mapper.PartTimeJobMapper;
import com.wxwyz.springboot.model.Business;
import com.wxwyz.springboot.service.user.IBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BusinessServiceImpl implements IBusinessService {

    @Autowired
    private BusinessMapper businessMapper;

    @Autowired
    private PartTimeJobMapper partTimeJobMapper;

    @Override
    public List<Business> queryAllBusiness() {
        return businessMapper.queryAllBusiness();
    }

    @Override
    public Business queryOneBusiness(String username) {
        return businessMapper.queryOneBusiness(username);
    }

    @Override
    public Integer updateAJobState(Integer jobId, Integer state) {
        return partTimeJobMapper.updateAJobState(jobId, state);
    }
}
