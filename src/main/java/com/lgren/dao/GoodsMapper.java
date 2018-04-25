package com.lgren.dao;

import com.lgren.pojo.po.Goods;

import java.util.List;

public interface GoodsMapper {
    List<Goods> selectAll();

    int deleteByPrimaryKey(Long goodsId);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Long goodsId);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);
}