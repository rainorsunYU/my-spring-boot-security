package com.wxwyz.springboot.mapper;

import com.wxwyz.springboot.dto.BusinessAllInfoDTO;
import com.wxwyz.springboot.model.PartTimeJob;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface PartTimeJobMapper {

    List<PartTimeJob> queryAllJob();

    Integer queryAllJobCounter();

    Integer queryJobCounterByParentId(Integer id);

    List<PartTimeJob> queryAllJobByPagination(Integer offerSet,Integer size);

    Integer insertOneJob(PartTimeJob partTimeJob);

    List<BusinessAllInfoDTO> queryJobAllInfo(Integer offerSet, Integer size);

    List<BusinessAllInfoDTO> queryJobClassify(@Param("offerSet") Integer offerSet,@Param("size") Integer size,@Param("jobType") String jobType);

    List<BusinessAllInfoDTO> queryJobClassify2(@Param("offerSet") Integer offerSet,@Param("size") Integer size,@Param("jobType1") String jobType1,@Param("jobType2") String jobType2,@Param("jobType3") String jobType3);

    List<BusinessAllInfoDTO> queryOneJobAllInfo(Integer offerSet, Integer size,Integer parentId);

    BusinessAllInfoDTO queryOneBusinessJob(Integer jobId);

    Integer updateViews(Integer jobId);

    Integer updateApplicants(Integer id);

    Integer queryJobApplicants(Integer id);

    Integer queryJObNeedNumber(Integer id);

    Integer updateAJobState(Integer jobId,Integer state);

    Integer deleteAJobApplicants(Integer jobId);

    Integer deleteJobByAdmin(Integer jobId);

    List<BusinessAllInfoDTO> queryDeleteJob(Integer offerSet,Integer size, Integer state);
}
