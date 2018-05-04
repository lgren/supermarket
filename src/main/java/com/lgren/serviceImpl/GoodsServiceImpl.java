package com.lgren.serviceImpl;

import com.lgren.dao.*;
import com.lgren.exception.SelectException;
import com.lgren.pojo.po.Goods;
import com.lgren.pojo.po.Order;
import com.lgren.service.CartGoodsService;
import com.lgren.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CartGoodsMapper cartGoodsService;
    @Autowired
    private CollectGoodsMapper collectGoodsMapper;


    @Override
    public int deleteByShopId(Long shopId) {
        List<Goods> goodsList = getGoodsByShopId(shopId);
        if (goodsList.size() == 0) {
            return 0;
        }
        goodsList.stream().map(g -> g.getGoodsId()).forEach(g -> {
            cartGoodsService.deleteByGoodsId(g);
            collectGoodsMapper.deleteByGoodsId(g);
        });
        orderMapper.deleteByShopId(shopId);
        goodsMapper.deleteByShopId(shopId);
        return 1;
    }

    @Override
    public Goods selectByGoodsIdAndShopId(Long goodsId, Long userId) {
        return goodsMapper.selectByGoodsIdAndShopId(goodsId,userId);
    }

    @Override
    public List<Goods> getGoodsByShopId(Long shopId) {
        return goodsMapper.getGoodsByShopId(shopId);
    }

    @Override
    public List<Goods> selectAll() {
        return goodsMapper.selectAll();
    }

    @Override
    public int deleteByPrimaryKey(Long goodsId) {
        return goodsMapper.deleteByPrimaryKey(goodsId);
    }

    @Override
    public int insert(Goods record) {
        return goodsMapper.insert(record);
    }

    @Override
    public int insertSelective(Goods record) {
        return goodsMapper.insertSelective(record);
    }

    @Override
    public Goods selectByPrimaryKey(Long goodsId) {
        return goodsMapper.selectByPrimaryKey(goodsId);
    }

    @Override
    public int updateByPrimaryKeySelective(Goods record) {
        return goodsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Goods record) {
        return goodsMapper.updateByPrimaryKey(record);
    }
}