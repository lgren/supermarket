package com.lgren.serviceImpl;

import com.lgren.dao.OrderMapper;
import com.lgren.pojo.po.Order;
import com.lgren.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;


    @Override
    public List<Order> getOrderListByUserId(Long userId) {
        return orderMapper.getOrderListByUserId(userId);
    }

    @Override
    public List<Order> getOrderListByShopId(Long shopId) {
        return orderMapper.getOrderListByShopId(shopId);
    }

    @Override
    public List<Order> getOrderListByGoodsId(Long goodsId) {
        return orderMapper.getOrderListByGoodsId(goodsId);
    }

    @Override
    public Order getOrderByUserIdAndgoodsId(Long userId, Long goodsId) {
        return orderMapper.getOrderByUserIdAndgoodsId(userId,goodsId);
    }

    @Override
    public List<Order> selectAll() {
        return orderMapper.selectAll();
    }

    @Override
    public int deleteByPrimaryKey(Long orderId) {
        return orderMapper.deleteByPrimaryKey(orderId);
    }

    @Override
    public int insert(Order record) {
        return orderMapper.insert(record);
    }

    @Override
    public int insertSelective(Order record) {
        return orderMapper.insertSelective(record);
    }

    @Override
    public Order selectByPrimaryKey(Long orderId) {
        return orderMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public int updateByPrimaryKeySelective(Order record) {
        return orderMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Order record) {
        return orderMapper.updateByPrimaryKey(record);
    }
}