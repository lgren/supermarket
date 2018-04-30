package com.lgren.serviceImpl;

import com.lgren.dao.CollectGoodsMapper;
import com.lgren.dao.CollectMapper;
import com.lgren.pojo.po.CollectGoods;
import com.lgren.service.CollectGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CollectGoodsServiceImpl implements CollectGoodsService {
    @Autowired
    private CollectGoodsMapper collectGoodsMapper;
    @Autowired
    private CollectMapper collectMapper;


    @Override
    public int selectByUserIdandGoodsId(Long userId, Long goodsId) {
        return collectGoodsMapper.selectByCollectIdandGoodsId(collectMapper.getCollectByUserId(userId).getCollectId(),goodsId);
    }

    @Override
    public int insertByUserIdAndGoodsId(Long userId, Long goodsId) {
        CollectGoods collectGoods = new CollectGoods(null,collectMapper.getCollectByUserId(userId).getCollectId(),goodsId,new Date(System.currentTimeMillis()));
        return collectGoodsMapper.insert(collectGoods);
    }

    @Override
    public List<CollectGoods> getCollectGoodsByCollectId(Long collectId) {
        return collectGoodsMapper.getCollectGoodsByCollectId(collectId);
    }

    @Override
    public List<CollectGoods> getCollectGoodsByGoodsId(Long goodsId) {
        return collectGoodsMapper.getCollectGoodsByGoodsId(goodsId);
    }

    @Override
    public List<CollectGoods> selectAll() {
        return collectGoodsMapper.selectAll();
    }

    @Override
    public int deleteByPrimaryKey(Long collectGoodsId) {
        return collectGoodsMapper.deleteByPrimaryKey(collectGoodsId);
    }

    @Override
    public int insert(CollectGoods record) {
        return collectGoodsMapper.insert(record);
    }

    @Override
    public int insertSelective(CollectGoods record) {
        return collectGoodsMapper.insertSelective(record);
    }

    @Override
    public CollectGoods selectByPrimaryKey(Long collectGoodsId) {
        return collectGoodsMapper.selectByPrimaryKey(collectGoodsId);
    }

    @Override
    public int updateByPrimaryKeySelective(CollectGoods record) {
        return collectGoodsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CollectGoods record) {
        return collectGoodsMapper.updateByPrimaryKey(record);
    }
}