package com.wxwyz.springboot.controller;

import com.wxwyz.springboot.dto.BusinessAllInfoDTO;
import com.wxwyz.springboot.dto.JobPage2DTO;
import com.wxwyz.springboot.service.pagination.impl.JobPageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private JobPageServiceImpl jobPageServiceImpl;

    @RequestMapping("/admin/manager")
    public String toAdmin(){
        return "admin/adminmanager";
    }

    @RequestMapping("/admin/all/job")
    @ResponseBody
    public List<BusinessAllInfoDTO> busAllInfo(@RequestParam("page") Integer page,
                                               @RequestParam("size") Integer size,
                                               HttpServletRequest request){
        JobPage2DTO page2DTO = jobPageServiceImpl.queryAllInfoJob(page, size);
        request.getSession().setAttribute("oneBusJobTotal",page2DTO.getTotalRecord());
        List<BusinessAllInfoDTO> businessAllInfoDTOS = page2DTO.getList();
//        for (int i = 0; i < businessAllInfoDTOS.size(); i++) {
//            businessAllInfoDTOS.get(i).setComments(commentServiceImpl.queryAJobCommNum(businessAllInfoDTOS.get(i).getPartTimeJob().getJobId()));
//        }
        return businessAllInfoDTOS;
    }


    @GetMapping("/admin/all/deletejob")
    @ResponseBody
    public List<BusinessAllInfoDTO> busAllInfo2(@RequestParam("page") Integer page,
                                               @RequestParam("size") Integer size,
                                               @RequestParam("state") Integer state,
                                               HttpServletRequest request){
        JobPage2DTO page2DTO = jobPageServiceImpl.queryAllDeleteJob(page, size,state);
        request.getSession().setAttribute("oneBusJobTotal",page2DTO.getTotalRecord());
        List<BusinessAllInfoDTO> businessAllInfoDTOS = page2DTO.getList();
//        for (int i = 0; i < businessAllInfoDTOS.size(); i++) {
//            businessAllInfoDTOS.get(i).setComments(commentServiceImpl.queryAJobCommNum(businessAllInfoDTOS.get(i).getPartTimeJob().getJobId()));
//        }
        return businessAllInfoDTOS;
    }

    @DeleteMapping("/admin/delete/job")
    @ResponseBody
    public String deleteJobBUAdmin(@RequestParam("jid") Integer jobId){
        return String.valueOf(jobPageServiceImpl.deleteJobByAdmin(jobId));
    }
}
