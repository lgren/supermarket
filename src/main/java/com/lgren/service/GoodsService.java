package com.lgren.service;

import com.lgren.pojo.po.Goods;

import java.util.List;

public interface GoodsService {
    int deleteByShopId(Long shopId);

    Goods selectByGoodsIdAndShopId(Long goodsId, Long userId);

    List<Goods> getGoodsByShopId(Long shopId);

    List<Goods> selectAll();

    int deleteByPrimaryKey(Long goodsId);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Long goodsId);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);
}