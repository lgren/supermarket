package com.lgren.serviceImpl;

import com.lgren.dao.WarehouseMapper;
import com.lgren.pojo.po.Warehouse;
import com.lgren.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    @Autowired
    private WarehouseMapper warehouseMapper;


    @Override
    public List<Warehouse> selectAll() {
        return warehouseMapper.selectAll();
    }

    @Override
    public int deleteByPrimaryKey(Long warehouseId) {
        return warehouseMapper.deleteByPrimaryKey(warehouseId);
    }

    @Override
    public int insert(Warehouse record) {
        return warehouseMapper.insert(record);
    }

    @Override
    public int insertSelective(Warehouse record) {
        return warehouseMapper.insertSelective(record);
    }

    @Override
    public Warehouse selectByPrimaryKey(Long warehouseId) {
        return warehouseMapper.selectByPrimaryKey(warehouseId);
    }

    @Override
    public int updateByPrimaryKeySelective(Warehouse record) {
        return warehouseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Warehouse record) {
        return warehouseMapper.updateByPrimaryKey(record);
    }
}