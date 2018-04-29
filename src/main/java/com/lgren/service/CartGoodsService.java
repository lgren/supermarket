package com.lgren.service;

import com.lgren.pojo.dto.CartGoodsDTO;
import com.lgren.pojo.po.CartGoods;

import java.util.List;

public interface CartGoodsService {
    int deleteByPrimaryKeyAndType(Long cartGoodsId,Integer type);

    int selectByUserIdandGoodsId(Long userId, Long goodsId);

    int insertByUserIdAndGoodsId(Long userId ,CartGoodsDTO cartGoodsDTO);

    List<CartGoods> getCartGoodsByCartIdAndType(Long cartId,Integer type);

    List<CartGoods> getCartGoodsByGoodsId(Long goodsId);

    List<CartGoods> selectAll();

    int deleteByPrimaryKey(Long cartGoodsId);

    int insert(CartGoods record);

    int insertSelective(CartGoods record);

    CartGoods selectByPrimaryKey(Long cartGoodsId);

    int updateByPrimaryKeySelective(CartGoods record);

    int updateByPrimaryKey(CartGoods record);
}