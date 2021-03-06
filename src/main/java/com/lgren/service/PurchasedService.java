package com.lgren.service;

import com.lgren.pojo.po.Purchased;

import java.util.List;

public interface PurchasedService {
    Purchased selectByOrderIdAndEvaluation(Long orderId, Integer evaluation);

    Purchased selectByOrderId(Long orderId);

    List<Purchased> selectAll();

    int deleteByPrimaryKey(Long purchasedId);

    int insert(Purchased record);

    int insertSelective(Purchased record);

    Purchased selectByPrimaryKey(Long purchasedId);

    int updateByPrimaryKeySelective(Purchased record);

    int updateByPrimaryKey(Purchased record);
}