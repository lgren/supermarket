package com.lgren.dao;

import com.lgren.pojo.po.Refund;

import java.util.List;

public interface RefundMapper {
    List<Refund> selectAll();

    int deleteByPrimaryKey(Long refundId);

    int insert(Refund record);

    int insertSelective(Refund record);

    Refund selectByPrimaryKey(Long refundId);

    int updateByPrimaryKeySelective(Refund record);

    int updateByPrimaryKey(Refund record);
}