package com.lgren.serviceImpl;

import com.lgren.dao.CartGoodsMapper;
import com.lgren.dao.CartMapper;
import com.lgren.pojo.dto.CartGoodsDTO;
import com.lgren.pojo.po.CartGoods;
import com.lgren.service.CartGoodsService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartGoodsServiceImpl implements CartGoodsService {
    @Autowired
    private Mapper mapper;
    @Autowired
    private CartGoodsMapper cartGoodsMapper;
    @Autowired
    private CartMapper cartMapper;


    @Override
    public int deleteByPrimaryKeyAndType(Long cartGoodsId, Integer type) {
        return cartGoodsMapper.deleteByPrimaryKeyAndType(cartGoodsId,type);
    }

    @Override
    public Long selectByUserIdandGoodsId(Long userId, Long goodsId) {
        return cartGoodsMapper.selectByCartIdandGoodsId(cartMapper.getCartByUserId(userId).getCartId(),goodsId);
    }

    @Override
    public int insertByUserIdAndGoodsId(Long userId, CartGoodsDTO cartGoodsDTO) {
        CartGoods cartGoods = mapper.map(cartGoodsDTO,CartGoods.class);
        return cartGoodsMapper.insert(cartGoods);
    }

    @Override
    public List<CartGoods> getCartGoodsByCartIdAndType(Long cartId,Integer type) {

        return type==null?cartGoodsMapper.getCartGoodsByCartId(cartId) : cartGoodsMapper.getCartGoodsByCartIdAndType(cartId,type);
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
        return cartGoodsMapper.deleteByPrimaryKeyAndType(cartGoodsId,0);
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