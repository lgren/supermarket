package com.lgren.dao;

import com.lgren.pojo.po.Shop;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ShopMapper {
    Shop getShopByName(String name);

    @Select("select count(shop_id) from tb_shop where user_id = #{userId,jdbcType=BIGINT}")
    int getShopCountByUserId(Long userId);

    List<Shop> getShopByUserId(Long userId);

    List<Shop> selectAll();

    List<Shop> selectFind(String content);

    int deleteByPrimaryKey(Long shopId);

    int insert(Shop record);

    int insertSelective(Shop record);

    Shop selectByPrimaryKey(Long shopId);

    int updateByPrimaryKeySelective(Shop record);

    int updateByPrimaryKey(Shop record);
}