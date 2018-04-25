package com.lgren.serviceImpl;

import com.lgren.dao.CollectGoodsMapper;
import com.lgren.pojo.po.CollectGoods;
import com.lgren.service.CollectGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectGoodsServiceImpl implements CollectGoodsService {
    @Autowired
    private CollectGoodsMapper collectGoodsMapper;


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