package com.wxwyz.springboot.controller;

import com.wxwyz.springboot.service.comment.impl.CommentServiceImpl;
import com.wxwyz.springboot.service.pagination.impl.JobPageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class RouterController {

    @Autowired
    private JobPageServiceImpl jobPageServiceImpl;

    @Autowired
    private CommentServiceImpl commentServiceImpl;

    @RequestMapping({"/","/index"})
    public String index(){
        return "index";
    }

    @RequestMapping("/info/index")
    public String infoIndex(){
        return "info/index";
    }

    @RequestMapping("/user/upload")
    public String uploadJob(HttpServletRequest request){
        String token = UUID.randomUUID().toString();
        request.getSession().setAttribute("token",token);
        return "info/add";
    }

    @RequestMapping("/user/classify1")
    public String toUserHome1(){
        return "userClassify/classify1";
    }

    @RequestMapping("/user/classify2")
    public String toUserHom2(){
        return "userClassify/classify2";
    }

    @RequestMapping("/user/classify3")
    public String toUserHome3(){
        return "userClassify/classify3";
    }

    @RequestMapping("/user/classify4")
    public String toUserHome4(){
        return "userClassify/classify4";
    }

    @RequestMapping("/bus/home")
    public String toBusHome(){
        return "busindex";
    }

    @RequestMapping("/bus/classify1")
    public String toBusHome1(){
        return "busClassify/classify1";
    }

    @RequestMapping("/bus/classify2")
    public String toBusHom2(){
        return "busClassify/classify2";
    }

    @RequestMapping("/bus/classify3")
    public String toBusHome3(){
        return "busClassify/classify3";
    }

    @RequestMapping("/bus/classify4")
    public String toBusHome4(){
        return "busClassify/classify4";
    }


}
