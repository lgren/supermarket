package com.lgren.dao;

import com.lgren.pojo.po.CartGoods;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CartGoodsMapper {
    @Delete("delete from tb_cart_goods where goods_id=#{goodsId,jdbcType=BIGINT}")
    int deleteByGoodsId(Long goodsId);

    @Select("select cart_goods_id from tb_cart_goods where want_pay_time = #{wantPayTime,jdbcType = BIGINT}")
    Long getCartGoodsByWantPayTime(Long wantPayTime);

    @Update("update tb_cart_goods set type = #{param2,jdbcType=INTEGER}, want_pay_time = #{param3,jdbcType=BIGINT} where cart_goods_id = #{param1,jdbcType=BIGINT}")
    int updateTypeAndWantPayTime(Long cartGoodsId, Integer type, Long wantPayTime);

    @Delete("delete from tb_cart_goods where cart_goods_id = #{param1,jdbcType=BIGINT} and type = #{param2,jdbcType=INTEGER}")
    int deleteByPrimaryKeyAndType(Long cartGoodsId,Integer type);

    @Select("select cart_goods_id from tb_cart_goods where cart_id = #{param1,jdbcType=BIGINT} and goods_id = #{param2,jdbcType=BIGINT}")
    Long selectByCartIdandGoodsId(Long cartId, Long goodsId);

    List<CartGoods> getCartGoodsByCartId(Long cartId);

    List<CartGoods> getCartGoodsByCartIdAndType(Long cartId,Integer type);

    List<CartGoods> getCartGoodsByGoodsId(Long goodsId);

    List<CartGoods> selectAll();

    int deleteByPrimaryKey(Long cartGoodsId);

    int insert(CartGoods record);

    int insertSelective(CartGoods record);

    CartGoods selectByPrimaryKey(Long cartGoodsId);

    int updateByPrimaryKeySelective(CartGoods record);

    int updateByPrimaryKey(CartGoods record);
}