package com.lgren.dao;

import com.lgren.pojo.po.Warehouse;

import java.util.List;

public interface WarehouseMapper {
    List<Warehouse> selectAll();

    int deleteByPrimaryKey(Long warehouseId);

    int insert(Warehouse record);

    int insertSelective(Warehouse record);

    Warehouse selectByPrimaryKey(Long warehouseId);

    int updateByPrimaryKeySelective(Warehouse record);

    int updateByPrimaryKey(Warehouse record);
}