package com.lgren.dao;

import com.lgren.pojo.po.Order;

import java.util.List;

public interface OrderMapper {
    List<Order> selectAll();

    int deleteByPrimaryKey(Long orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}