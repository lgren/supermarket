package com.lgren.serviceImpl;

import com.lgren.dao.ReceivingAddressMapper;
import com.lgren.pojo.po.ReceivingAddress;
import com.lgren.service.ReceivingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceivingAddressServiceImpl implements ReceivingAddressService {
    @Autowired
    private ReceivingAddressMapper receivingAddressMapper;


    @Override
    public List<ReceivingAddress> selectAll() {
        return receivingAddressMapper.selectAll();
    }

    @Override
    public int deleteByPrimaryKey(Long receivingAddressId) {
        return receivingAddressMapper.deleteByPrimaryKey(receivingAddressId);
    }

    @Override
    public int insert(ReceivingAddress record) {
        return receivingAddressMapper.insert(record);
    }

    @Override
    public int insertSelective(ReceivingAddress record) {
        return receivingAddressMapper.insertSelective(record);
    }

    @Override
    public ReceivingAddress selectByPrimaryKey(Long receivingAddressId) {
        return receivingAddressMapper.selectByPrimaryKey(receivingAddressId);
    }

    @Override
    public int updateByPrimaryKeySelective(ReceivingAddress record) {
        return receivingAddressMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ReceivingAddress record) {
        return receivingAddressMapper.updateByPrimaryKey(record);
    }
}