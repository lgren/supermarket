package com.lgren.serviceImpl;

import com.lgren.dao.ShopMapper;
import com.lgren.pojo.po.Shop;
import com.lgren.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopMapper shopMapper;


    @Override
    public int getShopCountByUserId(Long userId) {
        return shopMapper.getShopCountByUserId(userId);
    }

    @Override
    public List<Shop> getShopByUserId(Long userId) {
        return shopMapper.getShopByUserId(userId);
    }

    @Override
    public List<Shop> selectAll() {
        return shopMapper.selectAll();
    }

    @Override
    public int deleteByPrimaryKey(Long shopId) {
        return shopMapper.deleteByPrimaryKey(shopId);
    }

    @Override
    public int insert(Shop record) {
        return shopMapper.insert(record);
    }

    @Override
    public int insertSelective(Shop record) {
        return shopMapper.insertSelective(record);
    }

    @Override
    public Shop selectByPrimaryKey(Long shopId) {
        return shopMapper.selectByPrimaryKey(shopId);
    }

    @Override
    public int updateByPrimaryKeySelective(Shop record) {
        return shopMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Shop record) {
        return shopMapper.updateByPrimaryKey(record);
    }
}