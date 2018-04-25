package com.lgren.serviceImpl;

import com.lgren.dao.RefundMapper;
import com.lgren.pojo.po.Refund;
import com.lgren.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefundServiceImpl implements RefundService {
    @Autowired
    private RefundMapper refundMapper;


    @Override
    public List<Refund> selectAll() {
        return refundMapper.selectAll();
    }

    @Override
    public int deleteByPrimaryKey(Long refundId) {
        return refundMapper.deleteByPrimaryKey(refundId);
    }

    @Override
    public int insert(Refund record) {
        return refundMapper.insert(record);
    }

    @Override
    public int insertSelective(Refund record) {
        return refundMapper.insertSelective(record);
    }

    @Override
    public Refund selectByPrimaryKey(Long refundId) {
        return refundMapper.selectByPrimaryKey(refundId);
    }

    @Override
    public int updateByPrimaryKeySelective(Refund record) {
        return refundMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Refund record) {
        return refundMapper.updateByPrimaryKey(record);
    }
}