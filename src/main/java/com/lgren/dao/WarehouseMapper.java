package com.lgren.dao;

import com.lgren.pojo.po.Warehouse;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface WarehouseMapper {
    @Update("update tb_warehouse set number = #{param3,jdbcType=INTEGER} where shop_id = #{param1,jdbcType=BIGINT} and goods_id = #{param2,jdbcType=BIGINT}")
    int updateNumberByShopIdAndGoodsId(Long shopId, Long goodsId, Integer number);

    Warehouse getWarehouseByShopId(Long shopId);

    Warehouse getWarehouseByShopIdAndGoodsId(Long shopId, Long goodsId);

    List<Warehouse> selectAll();

    int deleteByPrimaryKey(Long warehouseId);

    int insert(Warehouse record);

    int insertSelective(Warehouse record);

    Warehouse selectByPrimaryKey(Long warehouseId);

    int updateByPrimaryKeySelective(Warehouse record);

    int updateByPrimaryKey(Warehouse record);
}