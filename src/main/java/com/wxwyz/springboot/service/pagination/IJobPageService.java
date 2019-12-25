package com.wxwyz.springboot.service.pagination;

import com.wxwyz.springboot.dto.BusinessAllInfoDTO;
import com.wxwyz.springboot.dto.JobPage2DTO;
import com.wxwyz.springboot.dto.JobPageDTO;
import com.wxwyz.springboot.model.PartTimeJob;

public interface IJobPageService {

    JobPageDTO queryAllJobByPagination(Integer page, Integer size);

    JobPage2DTO queryAllInfoJob(Integer page, Integer size);

    JobPage2DTO queryAllDeleteJob(Integer page, Integer size,Integer state);

    Integer insertOneJob(PartTimeJob job);

    BusinessAllInfoDTO queryOneBusinessJob(Integer jobId);

    Integer updateViews(Integer jobId);

    Integer updateApplicants(Integer jobId);

    Integer queryAJobApplicants(Integer jobId);

    Integer queryAJobNeedNumber(Integer jobId);

    JobPage2DTO queryOneBusJobInfo(Integer page, Integer size, Integer parentId);

    JobPage2DTO queryAllByClassify(Integer page,Integer size,String jobType);

    JobPage2DTO queryAllByClassify2(Integer page,Integer size,String jobType1,String jobType2,String jobType3);

    Integer deleteApplicant(Integer jobId);

    Integer deleteJobByAdmin(Integer jobId);
}
