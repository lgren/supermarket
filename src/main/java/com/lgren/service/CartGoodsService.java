package com.lgren.service;

import com.lgren.pojo.po.CartGoods;

import java.util.List;

public interface CartGoodsService {
    int selectByUserIdandGoodsId(Long userId, Long goodsId);

    int insertByUserIdAndGoodsId(Long userId ,Long goodsId);

    List<CartGoods> getCartGoodsByCartId(Long cartId);

    List<CartGoods> getCartGoodsByGoodsId(Long goodsId);

    List<CartGoods> selectAll();

    int deleteByPrimaryKey(Long cartGoodsId);

    int insert(CartGoods record);

    int insertSelective(CartGoods record);

    CartGoods selectByPrimaryKey(Long cartGoodsId);

    int updateByPrimaryKeySelective(CartGoods record);

    int updateByPrimaryKey(CartGoods record);
}