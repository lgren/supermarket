package com.lgren.service;

import com.lgren.pojo.po.Cart;

import java.util.List;

public interface CartService {
    Cart getCartByUserId(Long userId);

    List<Cart> selectAll();

    int deleteByPrimaryKey(Long cartId);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Long cartId);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);
}