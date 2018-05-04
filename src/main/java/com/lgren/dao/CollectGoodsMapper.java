package com.lgren.dao;

import com.lgren.pojo.po.CollectGoods;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CollectGoodsMapper {
    @Delete("delete from tb_collect_goods where goods_id=#{goodsId,jdbcType=BIGINT}")
    int deleteByGoodsId(Long goodsId);

    @Select("select count(collect_goods_id) from tb_collect_goods where collect_id = #{param1} and goods_id = #{param2}")
    int selectByCollectIdandGoodsId(Long collectId, Long goodsId);

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