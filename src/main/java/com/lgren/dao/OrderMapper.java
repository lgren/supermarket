package com.lgren.dao;

import com.lgren.pojo.po.Order;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface OrderMapper {
    Order getOrderByUserIdAndgoodsId(Long userId, Long goodsId);

    @Select("select order_id from tb_order where state = #{param1,jdbcType=INTEGER} and user_id = #{param2,jdbcType=BIGINT} and goods_id = #{param3,jdbcType=BIGINT}")
    Long getOrderIdByStateAndUserIdAndGoodsId(Integer state, Long userId, Long goodsId);

    @Select("select order_id from tb_order where order_time = #{param1,jdbcType=TIMESTAMP} and user_id = #{param2,jdbcType=BIGINT}")
    Long getOrderIdByTimeAndUserId(Date time, Long userId);

    List<Order> selectAll();

    int deleteByPrimaryKey(Long orderId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}