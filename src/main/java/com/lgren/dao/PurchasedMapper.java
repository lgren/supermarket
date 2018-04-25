package com.lgren.dao;

import com.lgren.pojo.po.Purchased;

import java.util.List;

public interface PurchasedMapper {
    List<Purchased> selectAll();

    int deleteByPrimaryKey(Long purchasedId);

    int insert(Purchased record);

    int insertSelective(Purchased record);

    Purchased selectByPrimaryKey(Long purchasedId);

    int updateByPrimaryKeySelective(Purchased record);

    int updateByPrimaryKey(Purchased record);
}