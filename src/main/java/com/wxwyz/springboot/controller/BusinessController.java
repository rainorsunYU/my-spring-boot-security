package com.wxwyz.springboot.controller;

import com.wxwyz.springboot.dto.BusinessAllInfoDTO;
import com.wxwyz.springboot.dto.JobPage2DTO;
import com.wxwyz.springboot.model.Business;
import com.wxwyz.springboot.service.comment.impl.CommentServiceImpl;
import com.wxwyz.springboot.service.pagination.impl.JobPageServiceImpl;
import com.wxwyz.springboot.service.user.impl.BusinessServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class BusinessController {

    @Autowired
    private BusinessServiceImpl businessServiceImpl;

    @Autowired
    private JobPageServiceImpl jobPageServiceImpl;

    @Autowired
    private CommentServiceImpl commentServiceImpl;

    @RequestMapping("/bus/login")
    public String toBusLogin(){
        return "user/buslogin";
    }


    @PostMapping("/bus/verify")
    @ResponseBody
    public String busVerify(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpServletRequest request){

        String result = "-1";
        Business business = businessServiceImpl.queryOneBusiness(username);
        if ( business != null ){
            if (business.getPassword().equals(password)){
                request.getSession().setAttribute("businessLogin",business);
                return "1";
            }else {
                return "0";
            }
        }
        return result;
    }

    @RequestMapping("/bus/logout")
    public String userLogout(HttpServletRequest request){
        request.getSession().removeAttribute("businessLogin");
        return "redirect:/";
    }

    @GetMapping("/business/job")
    @ResponseBody
    public List<BusinessAllInfoDTO> toBusDetail(@RequestParam("page") Integer page,
                                                @RequestParam("size") Integer size,
                                                @RequestParam("bid") Integer bid,
                                                HttpServletRequest request){

        JobPage2DTO page2DTO = jobPageServiceImpl.queryOneBusJobInfo(page, size, bid);
        request.getSession().setAttribute("oneBusJobTotal",page2DTO.getTotalRecord());
        List<BusinessAllInfoDTO> businessAllInfoDTOS = page2DTO.getList();
        for (int i = 0; i < businessAllInfoDTOS.size(); i++) {
            businessAllInfoDTOS.get(i).setComments(commentServiceImpl.queryAJobCommNum(businessAllInfoDTOS.get(i).getPartTimeJob().getJobId()));
        }
        return businessAllInfoDTOS;
    }

    @RequestMapping("/bus/activate")
    public String toActivate(){
        return "user/activate";
    }

    @DeleteMapping("/delete/job")
    @ResponseBody
    public String deleteAJob(@RequestParam("jid") Integer jid,
                             @RequestParam("state") Integer state){

        Integer updateAJobState = businessServiceImpl.updateAJobState(jid, state);
        if (updateAJobState == 1){
            return "1";
        }else{ return "-1"; }
    }

    @RequestMapping("/bus/setting")
    public String toSetting(){
        return "user/busset";
    }

    @RequestMapping("/bus/reg")
    public String toReg(){
        return "user/busreg";
    }
}
