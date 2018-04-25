package com.lgren.serviceImpl;

import com.lgren.dao.CartMapper;
import com.lgren.pojo.po.Cart;
import com.lgren.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartMapper cartMapper;


    @Override
    public Cart getCartByUserId(Long userId) {
        return cartMapper.getCartByUserId(userId);
    }

    @Override
    public List<Cart> selectAll() {
        return cartMapper.selectAll();
    }

    @Override
    public int deleteByPrimaryKey(Long cartId) {
        return cartMapper.deleteByPrimaryKey(cartId);
    }

    @Override
    public int insert(Cart record) {
        return cartMapper.insert(record);
    }

    @Override
    public int insertSelective(Cart record) {
        return cartMapper.insertSelective(record);
    }

    @Override
    public Cart selectByPrimaryKey(Long cartId) {
        return cartMapper.selectByPrimaryKey(cartId);
    }

    @Override
    public int updateByPrimaryKeySelective(Cart record) {
        return cartMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Cart record) {
        return cartMapper.updateByPrimaryKey(record);
    }
}