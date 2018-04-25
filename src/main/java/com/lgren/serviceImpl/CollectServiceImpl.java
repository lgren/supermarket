package com.lgren.serviceImpl;

import com.lgren.dao.CollectMapper;
import com.lgren.pojo.po.Collect;
import com.lgren.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectMapper collectMapper;


    @Override
    public List<Collect> selectAll() {
        return collectMapper.selectAll();
    }

    @Override
    public int deleteByPrimaryKey(Long collectId) {
        return collectMapper.deleteByPrimaryKey(collectId);
    }

    @Override
    public int insert(Collect record) {
        return collectMapper.insert(record);
    }

    @Override
    public int insertSelective(Collect record) {
        return collectMapper.insertSelective(record);
    }

    @Override
    public Collect selectByPrimaryKey(Long collectId) {
        return collectMapper.selectByPrimaryKey(collectId);
    }

    @Override
    public int updateByPrimaryKeySelective(Collect record) {
        return collectMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Collect record) {
        return collectMapper.updateByPrimaryKey(record);
    }
}