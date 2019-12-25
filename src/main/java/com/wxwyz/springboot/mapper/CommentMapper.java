package com.wxwyz.springboot.mapper;

import com.wxwyz.springboot.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CommentMapper {

    Integer insertAComment(Comment comment);

    Integer queryOneJobCommNum(Integer jobId);

    List<Comment> queryAllCommentByPage(Integer offset, Integer size, Integer jobId);

    Integer queryAllCommentNum();
}
