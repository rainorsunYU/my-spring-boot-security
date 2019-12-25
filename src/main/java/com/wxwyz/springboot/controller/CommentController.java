package com.wxwyz.springboot.controller;

import com.wxwyz.springboot.dto.CommentPageDTO;
import com.wxwyz.springboot.model.Comment;
import com.wxwyz.springboot.service.comment.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentServiceImpl commentServiceImpl;

    @GetMapping("/comment/info")
    @ResponseBody
    public List<Comment> commentInfo(@RequestParam("page") Integer page,
                                     @RequestParam("size") Integer size,
                                     @RequestParam("jobId") Integer jobId,
                                     HttpServletRequest request){

        CommentPageDTO commentPageDTO = commentServiceImpl.queryAllCommentByPage(page,size,jobId);
        request.getSession().setAttribute("commentTotal",commentPageDTO.getTotalRecord());
        return commentPageDTO.getList();
    }

    @PostMapping("/comment/add")
    @ResponseBody
    public String addComment(@RequestParam("jobId") Integer jobId,
                             @RequestParam("content") String content,
                             @RequestParam("commentator") String commentator,
                             @RequestParam("avatar") String avatar){

        Comment comment = new Comment();
        comment.setJobId(jobId);
        comment.setCommentator(commentator);
        comment.setAvatar(avatar);
        comment.setContent(content);
        comment.setCreateTime(new Date());
        String msg = String.valueOf(commentServiceImpl.insertAComment(comment));
        return msg;
    }
}
