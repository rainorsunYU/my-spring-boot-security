package com.wxwyz.springboot.dto;

import com.wxwyz.springboot.model.Comment;
import lombok.Data;

import java.util.List;

@Data
public class CommentPageDTO {


    private Integer page;

    private Integer size;

    private Integer totalRecord;

    private List<Comment> list;

    private Integer totalPages;

    private Integer offerSet;

    public void setTotalRecord(Integer totalRecord) {

        this.totalRecord = totalRecord;

        if (totalRecord % size == 0) {
            totalPages = totalRecord / size;
        } else {
            totalPages = totalRecord / size + 1;
        }
    }

    public Integer getOfferSet() {

        return (page - 1) * size;
    }

}
