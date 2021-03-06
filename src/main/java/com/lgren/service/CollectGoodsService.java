package com.lgren.service;

import com.lgren.pojo.po.CollectGoods;

import java.util.List;

public interface CollectGoodsService {
    int selectByUserIdandGoodsId(Long userId ,Long goodsId);

    int insertByUserIdAndGoodsId(Long userId ,Long goodsId);

    List<CollectGoods> getCollectGoodsByCollectId(Long collectId);

    List<CollectGoods> getCollectGoodsByGoodsId(Long goodsId);

    List<CollectGoods> selectAll();

    int deleteByPrimaryKey(Long collectGoodsId);

    int insert(CollectGoods record);

    int insertSelective(CollectGoods record);

    CollectGoods selectByPrimaryKey(Long collectGoodsId);

    int updateByPrimaryKeySelective(CollectGoods record);

    int updateByPrimaryKey(CollectGoods record);
}