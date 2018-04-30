package com.lgren.service;

import com.lgren.pojo.po.Order;

import java.util.List;

public interface OrderService {
    List<Order> getOrderListByUserId(Long userId);

    List<Order> getOrderListByShopId(Long shopId);

    List<Order> getOrderListByGoodsId(Long goodsId);

    Order getOrderByUserIdAndgoodsId(Long userId, Long goodsId);

    List<Order> selectAll();

    int deleteByPrimaryKey(Long orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}