package com.lgren.service;

import com.lgren.pojo.po.Shop;

import java.util.List;

public interface ShopService {
    int getShopCountByUserId(Long userId);

    List<Shop> getShopByUserId(Long userId);

    List<Shop> selectAll();

    int deleteByPrimaryKey(Long shopId);

    int insert(Shop record);

    int insertSelective(Shop record);

    Shop selectByPrimaryKey(Long shopId);

    int updateByPrimaryKeySelective(Shop record);

    int updateByPrimaryKey(Shop record);
}