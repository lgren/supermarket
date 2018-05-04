package com.lgren.service;

import com.lgren.pojo.po.Cart;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

public interface CartService {
    int deleteByGoodsId(Long goodsId);

    Cart getCartByUserId(Long userId);

    List<Cart> selectAll();

    int deleteByPrimaryKey(Long cartId);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Long cartId);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);
}