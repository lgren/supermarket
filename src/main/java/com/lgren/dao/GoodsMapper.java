package com.lgren.dao;

import com.lgren.pojo.po.Goods;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

public interface GoodsMapper {
    @Delete("delete from tb_goods where shop_id = #{shopId,jdbcType=BIGINT}")
    int deleteByShopId(Long shopId);

    Goods selectByGoodsIdAndShopId(Long goodsId, Long userId);

    List<Goods> getGoodsByShopId(Long shopId);

    List<Goods> selectAll();

    List<Goods> selectFind(String content);

    int deleteByPrimaryKey(Long goodsId);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Long goodsId);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);
}