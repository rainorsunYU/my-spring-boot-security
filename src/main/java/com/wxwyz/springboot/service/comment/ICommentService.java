package com.wxwyz.springboot.service.comment;

import com.wxwyz.springboot.dto.CommentPageDTO;
import com.wxwyz.springboot.model.Comment;

public interface ICommentService {

    Integer queryAJobCommNum(Integer jobId);

    CommentPageDTO queryAllCommentByPage(Integer page, Integer size,Integer jobId);

    Integer insertAComment(Comment comment);
}
