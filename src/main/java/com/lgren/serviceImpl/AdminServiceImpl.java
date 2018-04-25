package com.lgren.serviceImpl;

import com.lgren.dao.AdminMapper;
import com.lgren.pojo.po.Admin;
import com.lgren.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;


    @Override
    public List<Admin> selectAll() {
        return adminMapper.selectAll();
    }

    @Override
    public int deleteByPrimaryKey(Long adminId) {
        return adminMapper.deleteByPrimaryKey(adminId);
    }

    @Override
    public int insert(Admin record) {
        return adminMapper.insert(record);
    }

    @Override
    public int insertSelective(Admin record) {
        return adminMapper.insertSelective(record);
    }

    @Override
    public Admin selectByPrimaryKey(Long adminId) {
        return adminMapper.selectByPrimaryKey(adminId);
    }

    @Override
    public int updateByPrimaryKeySelective(Admin record) {
        return adminMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Admin record) {
        return adminMapper.updateByPrimaryKey(record);
    }
}