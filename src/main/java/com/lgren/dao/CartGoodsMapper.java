package com.lgren.dao;

import com.lgren.pojo.po.CartGoods;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CartGoodsMapper {
    @Select("select count(cart_goods_id) from tb_cart_goods where cart_id = #{param1} and goods_id = #{param2}")
    int selectByCartIdandGoodsId(Long cartId, Long goodsId);

    List<CartGoods> getCartGoodsByCartId(Long cartId);

    List<CartGoods> getCartGoodsByGoodsId(Long goodsId);

    List<CartGoods> selectAll();

    int deleteByPrimaryKey(Long cartGoodsId);

    int insert(CartGoods record);

    int insertSelective(CartGoods record);

    CartGoods selectByPrimaryKey(Long cartGoodsId);

    int updateByPrimaryKeySelective(CartGoods record);

    int updateByPrimaryKey(CartGoods record);
}