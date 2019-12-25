package com.wxwyz.springboot.mapper;

import com.wxwyz.springboot.model.Business;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface BusinessMapper {

    List<Business> queryAllBusiness();

    Business queryOneBusiness(String username);
}
