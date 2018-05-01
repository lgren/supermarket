package com.lgren.dao;

import com.lgren.pojo.po.Order;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

public interface OrderMapper {
    @Update("update tb_order set confirm = 1 where order_id = #{orderId,jdbcType=BIGINT}")
    int updateConfirm(Long orderId);

    @Update("update tb_order set send_goods = 1 where order_id = #{orderId,jdbcType=BIGINT}")
    int updateSendGoods(Long orderId);

    @Update("update tb_order set state = #{param2,jdbcType=INTEGER} where order_id = #{param1,jdbcType=BIGINT}")
    int updateState(Long orderId, Integer state);

    List<Order> getOrderListByUserId(Long userId);

    List<Order> getOrderListByShopId(Long ShopId);

    List<Order> getOrderListByGoodsId(Long GoodsId);

    Order getOrderByUserIdAndgoodsId(Long userId, Long goodsId);

    @Select("select order_id from tb_order where state = #{param1,jdbcType=INTEGER} and user_id = #{param2,jdbcType=BIGINT} and goods_id = #{param3,jdbcType=BIGINT} and order_time = #{param4}")
    Long getOrderIdByStateAndUserIdAndGoodsId(Integer state, Long userId, Long goodsId, String orderTime);

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