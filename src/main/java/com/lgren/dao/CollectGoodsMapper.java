package com.lgren.dao;

import com.lgren.pojo.po.CollectGoods;

import java.util.List;

public interface CollectGoodsMapper {
    List<CollectGoods> selectAll();

    int deleteByPrimaryKey(Long collectGoodsId);

    int insert(CollectGoods record);

    int insertSelective(CollectGoods record);

    CollectGoods selectByPrimaryKey(Long collectGoodsId);

    int updateByPrimaryKeySelective(CollectGoods record);

    int updateByPrimaryKey(CollectGoods record);
}