package com.lgren.service;

import com.lgren.pojo.po.Collect;

import java.util.List;

public interface CollectService {
    List<Collect> selectAll();

    int deleteByPrimaryKey(Long collectId);

    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(Long collectId);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);
}