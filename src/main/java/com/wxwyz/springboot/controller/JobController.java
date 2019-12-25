package com.wxwyz.springboot.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxwyz.springboot.dto.BusinessAllInfoDTO;
import com.wxwyz.springboot.dto.JobPage2DTO;
import com.wxwyz.springboot.dto.JobPageDTO;
import com.wxwyz.springboot.model.Enter;
import com.wxwyz.springboot.model.PartTimeJob;
import com.wxwyz.springboot.service.comment.impl.CommentServiceImpl;
import com.wxwyz.springboot.service.pagination.impl.JobPageServiceImpl;
import com.wxwyz.springboot.service.userEnter.impl.UserEnterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller

public class JobController {

    @Autowired
    private JobPageServiceImpl jobPageServiceImpl;

    @Autowired
    private CommentServiceImpl commentServiceImpl;

    @Autowired
    private UserEnterServiceImpl userEnterServiceImpl;

    @RequestMapping("/job/detail")
    public String jobDetail(@RequestParam("jobId") Integer jobId,
                            Model model) {

        jobPageServiceImpl.updateViews(jobId);
        BusinessAllInfoDTO businessJob = jobPageServiceImpl.queryOneBusinessJob(jobId);
        businessJob.setComments(commentServiceImpl.queryAJobCommNum(jobId));
        model.addAttribute("businessJob", businessJob);

        return "info/detail";
    }


    @GetMapping("/job/info")
    @ResponseBody
    public List<PartTimeJob> jobInfo(@RequestParam("page") Integer page,
                                     @RequestParam("size") Integer size,
                                     HttpServletRequest request) {

        JobPageDTO jobPageDTO = jobPageServiceImpl.queryAllJobByPagination(page, size);
        request.getSession().setAttribute("total", jobPageDTO.getTotalRecord());
        return jobPageDTO.getList();
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @PostMapping(value = "/add/job")
    @ResponseBody
    public String addOneJob(@RequestParam("token") String token,
                            @RequestParam("parentId") Integer parentId,
                            @RequestParam("jobType") String jobType,
                            @RequestParam("jobTitle") String jobTitle,
                            @RequestParam("jobContent") String jobContent,
                            @RequestParam("needNumber") Integer needNumber,
                            @RequestParam("jobSalary") Double jobSalary,
                            @RequestParam("jobLocation") String jobLocation,
                            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                            @RequestParam("startTime") Date startTime,
                            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                            @RequestParam("endTime") Date endTime,
                            HttpServletRequest request) {

        if (isRepeatSubmit(token, request) == true) {
            request.getSession().setAttribute("msgCode", "405");
            return "405";
        } else {
            Date creatDate = new Date();
            PartTimeJob partTimeJob = new PartTimeJob();
            partTimeJob.setToken(token);
            partTimeJob.setParentId(parentId);
            partTimeJob.setJobType(jobType);
            partTimeJob.setJobTitle(jobTitle);
            partTimeJob.setJobContent(jobContent);
            partTimeJob.setNeedNumber(needNumber);
            partTimeJob.setJobLocation(jobLocation);
            partTimeJob.setJobSalary(jobSalary);
            partTimeJob.setReleaseTime(creatDate);
            partTimeJob.setStartTime(startTime);
            partTimeJob.setEndTime(endTime);
            if (jobPageServiceImpl.insertOneJob(partTimeJob) == 1) {
                request.getSession().removeAttribute("token");
                return "1";
//                request.getSession().setAttribute("msg","success");
            } else {
                request.getSession().setAttribute("msgCode", "500");
                return "500";
            }
        }
    }

    @GetMapping("/job/info2")
    @ResponseBody
    public List<BusinessAllInfoDTO> findJobAllInfo(@RequestParam("page") Integer page,
                                                   @RequestParam("size") Integer size,
                                                   HttpServletRequest request,
                                                   Model model) {

        JobPage2DTO jobPage2DTO = jobPageServiceImpl.queryAllInfoJob(page, size);
        List<BusinessAllInfoDTO> allInfoDTOS = jobPage2DTO.getList();
        for (int i = 0; i < allInfoDTOS.size(); i++) {
            allInfoDTOS.get(i).setComments(commentServiceImpl.queryAJobCommNum(allInfoDTOS.get(i).getPartTimeJob().getJobId()));
        }
        request.getSession().setAttribute("total", jobPage2DTO.getTotalRecord());
        model.addAttribute("jobLists", allInfoDTOS);
        return allInfoDTOS;
    }


    @PutMapping("/user/enter")
    @ResponseBody
    public String userEnter(@RequestParam("userId") Integer id,
                            @RequestParam("jobId") Integer jobId) {

        Integer applicants = jobPageServiceImpl.queryAJobApplicants(jobId);
        Integer needNumber = jobPageServiceImpl.queryAJobNeedNumber(jobId);
        if (applicants > needNumber) {
            return "101"; //表示人数已满
        } else {
            if (jobPageServiceImpl.updateApplicants(jobId) == 1) {
                Enter enter = new Enter();
                enter.setUserId(id);
                enter.setJobId(jobId);
                enter.setEnterTime(new Date());
                if (userEnterServiceImpl.insertAUserEnter(enter) == 1) {
                    return "1";
                }
            } else {
                return "0"; //表示异常;
            }
        }
        return "-1";//表示异常;
    }


    private boolean isRepeatSubmit(String cltoken, HttpServletRequest request) {
        String client_token = cltoken;
        //1、如果用户提交的表单数据中没有token，则用户是重复提交了表单
        if (client_token == null) {
            return true;
        }
        //取出存储在Session中的token
        String server_token = (String) request.getSession().getAttribute("token");
        //2、如果当前用户的Session中不存在Token(令牌)，则用户是重复提交了表单
        if (server_token == null) {
            return true;
        }
        //3、存储在Session中的Token(令牌)与表单提交的Token(令牌)不同，则用户是重复提交了表单
        if (!client_token.equals(server_token)) {
            return true;
        }
        return false;
    }


    @GetMapping("/job/info2/classify1")
    @ResponseBody
    public List<BusinessAllInfoDTO> findJobAllClassify(@RequestParam("page") Integer page,
                                                       @RequestParam("size") Integer size,
                                                       @RequestParam("jobType") String jobType,
                                                       HttpServletRequest request,
                                                       Model model) {

        JobPage2DTO jobPage2DTO = jobPageServiceImpl.queryAllByClassify(page, size, jobType);
        List<BusinessAllInfoDTO> allInfoDTOS = jobPage2DTO.getList();
        for (int i = 0; i < allInfoDTOS.size(); i++) {
            allInfoDTOS.get(i).setComments(commentServiceImpl.queryAJobCommNum(allInfoDTOS.get(i).getPartTimeJob().getJobId()));
        }
        request.getSession().setAttribute("total1", jobPage2DTO.getTotalRecord());
        model.addAttribute("jobLists", allInfoDTOS);
        return allInfoDTOS;
    }

    @GetMapping("/job/info2/classify2")
    @ResponseBody
    public List<BusinessAllInfoDTO> findJobAllClassify2(@RequestParam("page") Integer page,
                                                       @RequestParam("size") Integer size,
                                                       @RequestParam("jobType1") String jobType1,
                                                       @RequestParam("jobType2") String jobType2,
                                                       @RequestParam("jobType3") String jobType3,
                                                       HttpServletRequest request,
                                                       Model model) {

        JobPage2DTO jobPage2DTO = jobPageServiceImpl.queryAllByClassify2(page, size, jobType1,jobType2, jobType3);
        List<BusinessAllInfoDTO> allInfoDTOS = jobPage2DTO.getList();
        for (int i = 0; i < allInfoDTOS.size(); i++) {
            allInfoDTOS.get(i).setComments(commentServiceImpl.queryAJobCommNum(allInfoDTOS.get(i).getPartTimeJob().getJobId()));
        }
        request.getSession().setAttribute("total1", jobPage2DTO.getTotalRecord());
        model.addAttribute("jobLists", allInfoDTOS);
        return allInfoDTOS;
    }

}
