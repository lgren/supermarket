package com.lgren.service;

import com.lgren.pojo.po.Refund;

import java.util.List;

public interface RefundService {
    List<Refund> selectAll();

    int deleteByPrimaryKey(Long refundId);

    int insert(Refund record);

    int insertSelective(Refund record);

    Refund selectByPrimaryKey(Long refundId);

    int updateByPrimaryKeySelective(Refund record);

    int updateByPrimaryKey(Refund record);
}