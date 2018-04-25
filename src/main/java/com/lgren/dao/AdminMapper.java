package com.lgren.dao;

import com.lgren.pojo.po.Admin;

import java.util.List;

public interface AdminMapper {
    List<Admin> selectAll();

    int deleteByPrimaryKey(Long adminId);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Long adminId);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}