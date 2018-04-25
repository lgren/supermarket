package com.lgren.api.remote;

import com.lgren.pojo.po.Warehouse;
import com.lgren.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/remote")
public class WarehouseRemote {
    @Autowired
    private WarehouseService warehouseService;
    //查询所有
    @RequestMapping(value = "/warehouse.do",method = RequestMethod.GET)
    public List<Warehouse> getAllWarehouse() {
        return warehouseService.selectAll();
    }
    //根据主键ID查询
    @RequestMapping(value = "/warehouse.do/{warehouseId}",method = RequestMethod.GET)
    public Warehouse getWarehouseById(@PathVariable("warehouseId") Long warehouseId) {
        return warehouseService.selectByPrimaryKey(warehouseId);
    }
    //添加
    @RequestMapping(value = "/warehouse.do",method = RequestMethod.POST)
    public boolean addWarehouse(Warehouse warehouse) {
        return warehouseService.insertSelective(warehouse) > 0 ? true : false;
    }
    //通过主键ID删除
    @RequestMapping(value = "/warehouse.do/{warehouseId}",method = RequestMethod.DELETE)
    public boolean deleteWarehouseById(@PathVariable("warehouseId") Long warehouseId) {
        return warehouseService.deleteByPrimaryKey(warehouseId) > 0 ? true : false;
    }
    //通过传输一个Warehouse对象修改
    @RequestMapping(value = "/warehouse.do",method = RequestMethod.PUT)
    public boolean updateWarehouseById(Warehouse warehouse) {
        return warehouseService.updateByPrimaryKeySelective(warehouse) > 0 ? true : false;
    }



}
