package com.lgren.dao;

import com.lgren.pojo.po.Cart;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

public interface CartMapper {
    @Delete("delete from tb_cart where goods_id=#{goodsId,jdbcType=BIGINT}")
    int deleteByGoodsId(Long goodsId);

    Cart getCartByUserId(Long userId);

    List<Cart> selectAll();

    int deleteByPrimaryKey(Long cartId);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Long cartId);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);
}