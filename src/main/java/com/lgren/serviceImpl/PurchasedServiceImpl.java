package com.lgren.serviceImpl;

import com.lgren.dao.PurchasedMapper;
import com.lgren.pojo.po.Purchased;
import com.lgren.service.PurchasedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchasedServiceImpl implements PurchasedService {
    @Autowired
    private PurchasedMapper purchasedMapper;


    @Override
    public List<Purchased> selectAll() {
        return purchasedMapper.selectAll();
    }

    @Override
    public int deleteByPrimaryKey(Long purchasedId) {
        return purchasedMapper.deleteByPrimaryKey(purchasedId);
    }

    @Override
    public int insert(Purchased record) {
        return purchasedMapper.insert(record);
    }

    @Override
    public int insertSelective(Purchased record) {
        return purchasedMapper.insertSelective(record);
    }

    @Override
    public Purchased selectByPrimaryKey(Long purchasedId) {
        return purchasedMapper.selectByPrimaryKey(purchasedId);
    }

    @Override
    public int updateByPrimaryKeySelective(Purchased record) {
        return purchasedMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Purchased record) {
        return purchasedMapper.updateByPrimaryKey(record);
    }
}