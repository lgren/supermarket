package com.lgren.serviceImpl;

import com.lgren.dao.CartGoodsMapper;
import com.lgren.dao.CartMapper;
import com.lgren.pojo.po.CartGoods;
import com.lgren.service.CartGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartGoodsServiceImpl implements CartGoodsService {
    @Autowired
    private CartGoodsMapper cartGoodsMapper;
    @Autowired
    private CartMapper cartMapper;


    @Override
    public int selectByUserIdandGoodsId(Long userId, Long goodsId) {
        return cartGoodsMapper.selectByCartIdandGoodsId(cartMapper.getCartByUserId(userId).getCartId(),goodsId);
    }

    @Override
    public int insertByUserIdAndGoodsId(Long userId, Long goodsId) {
        CartGoods cartGoods = new CartGoods();
        cartGoods.setGoodsId(goodsId);
        cartGoods.setCartId(cartMapper.getCartByUserId(userId).getCartId());
        return cartGoodsMapper.insert(cartGoods);
    }

    @Override
    public List<CartGoods> getCartGoodsByCartId(Long cartId) {
        return cartGoodsMapper.getCartGoodsByCartId(cartId);
    }

    @Override
    public List<CartGoods> getCartGoodsByGoodsId(Long goodsId) {
        return cartGoodsMapper.getCartGoodsByGoodsId(goodsId);
    }

    @Override
    public List<CartGoods> selectAll() {
        return cartGoodsMapper.selectAll();
    }

    @Override
    public int deleteByPrimaryKey(Long cartGoodsId) {
        return cartGoodsMapper.deleteByPrimaryKey(cartGoodsId);
    }

    @Override
    public int insert(CartGoods record) {
        return cartGoodsMapper.insert(record);
    }

    @Override
    public int insertSelective(CartGoods record) {
        return cartGoodsMapper.insertSelective(record);
    }

    @Override
    public CartGoods selectByPrimaryKey(Long cartGoodsId) {
        return cartGoodsMapper.selectByPrimaryKey(cartGoodsId);
    }

    @Override
    public int updateByPrimaryKeySelective(CartGoods record) {
        return cartGoodsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CartGoods record) {
        return cartGoodsMapper.updateByPrimaryKey(record);
    }
}