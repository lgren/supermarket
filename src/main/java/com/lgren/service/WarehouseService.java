package com.lgren.service;

import com.lgren.pojo.po.Warehouse;

import java.util.List;

public interface WarehouseService {
    List<Warehouse> selectAll();

    int deleteByPrimaryKey(Long warehouseId);

    int insert(Warehouse record);

    int insertSelective(Warehouse record);

    Warehouse selectByPrimaryKey(Long warehouseId);

    int updateByPrimaryKeySelective(Warehouse record);

    int updateByPrimaryKey(Warehouse record);
}