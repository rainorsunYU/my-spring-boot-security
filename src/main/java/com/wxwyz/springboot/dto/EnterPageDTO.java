package com.wxwyz.springboot.dto;

import lombok.Data;

import java.util.List;

@Data
public class EnterPageDTO {

    private Integer page;

    private Integer size;

    private Integer totalRecord;

    private List<UserEnterJobDTO> list;

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
