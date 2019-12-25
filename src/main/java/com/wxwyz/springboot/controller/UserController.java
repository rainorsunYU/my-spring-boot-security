package com.wxwyz.springboot.controller;

import com.wxwyz.springboot.dto.EnterPageDTO;
import com.wxwyz.springboot.dto.UserEnterJobDTO;
import com.wxwyz.springboot.model.User;
import com.wxwyz.springboot.service.pagination.impl.JobPageServiceImpl;
import com.wxwyz.springboot.service.user.impl.UserServiceImpl;
import com.wxwyz.springboot.service.userEnter.impl.UserEnterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private UserEnterServiceImpl userEnterServiceImpl;

    @Autowired
    private JobPageServiceImpl jobPageServiceImpl;

    @RequestMapping("/user/login")
    public String toUserLogin() {
        return "user/userlogin";
    }


    @PostMapping("/user/verify")
    @ResponseBody
    public String userLogin(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpServletRequest request) {

        String result = "-1";
        User user = userServiceImpl.queryOneUser(username);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                request.getSession().setAttribute("userLogin", user);
                return "1";
            } else {
                return "0";
            }
        }
        return result;
    }

    @RequestMapping("/user/logout")
    public String userLogout(HttpServletRequest request) {
        request.getSession().removeAttribute("userLogin");
        return "redirect:/";
    }

    @RequestMapping("/user/activate")
    public String toActivate() {
        return "user/activate2";
    }

    @GetMapping("/user/enter/job")
    @ResponseBody
    public List<UserEnterJobDTO> userEnterJob(@RequestParam("page") Integer page,
                                              @RequestParam("size") Integer size,
                                              @RequestParam("uid") Integer uid) {

        EnterPageDTO enterPageDTO = userEnterServiceImpl.queryOneUserEnter(page, size, uid);

        return enterPageDTO.getList();
    }

    @DeleteMapping("/user/delete/enter")
    @ResponseBody
    public String deleteEnterJob(@RequestParam("enterId") Integer enterId,
                                 @RequestParam("jobId") Integer jobId) {

        Integer deleteAEnterJob = userEnterServiceImpl.deleteAEnterJob(enterId);
        if (deleteAEnterJob == 1){
            if(jobPageServiceImpl.deleteApplicant(jobId) == 1){
                return "1";
            }
            else {
                return "0";
            }
        }
        return "-1";
    }
    @RequestMapping("/user/setting")
    public String toSetting(){
        return "user/set";
    }

    @RequestMapping("/user/reg")
    public String toReg(){
        return "user/reg";
    }
}
