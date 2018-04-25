/*
package com.lgren.api.moudle;

import com.lgren.pojo.dto.CartDTO;
import com.lgren.pojo.dto.WarehouseDTO;
import com.lgren.pojo.dto.GoodsDTO;
import com.lgren.pojo.po.Cart;
import com.lgren.pojo.po.Warehouse;
import com.lgren.pojo.po.Goods;
import com.lgren.pojo.vo.WarehouseVO;
import com.lgren.service.WarehouseService;
import com.lgren.service.CartService;
import com.lgren.service.GoodsService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class WarehouseApi {//OK
    @Autowired
    private Mapper mapper;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private CartService cartService;
    @Autowired
    private GoodsService goodsService;
    //查询所有
    public List<WarehouseVO> getAllWarehouse() {
        List<Warehouse> warehouseList = warehouseService.selectAll();
        List<WarehouseVO> warehouseVOList = getWarehouseVOList(warehouseList);
        return warehouseVOList;
    }
    public List<WarehouseVO> getWarehouseByCartId(Long cartId) {
        List<Warehouse> warehouseList = warehouseService.getWarehouseByCartId(cartId);
        List<WarehouseVO> warehouseVOList = getWarehouseVOList(warehouseList);
        return warehouseVOList;
    }
    public List<WarehouseVO> getWarehouseByGoodsId(Long goodsId) {
        List<Warehouse> warehouseList = warehouseService.getWarehouseByCartId(goodsId);
        List<WarehouseVO> warehouseVOList = getWarehouseVOList(warehouseList);
        return warehouseVOList;
    }
    //根据主键ID查询
    public WarehouseVO getWarehouseById(Long warehouseId) {
        Warehouse warehouse = warehouseService.selectByPrimaryKey(warehouseId);
        WarehouseVO warehouseVO = mapper.map(warehouse,WarehouseVO.class);

        Cart cart = cartService.selectByPrimaryKey(warehouse.getCartId());
        Goods goods = goodsService.selectByPrimaryKey(warehouse.getGoodsId());
        warehouseVO.setCartDTO(mapper.map(cart,CartDTO.class));
        warehouseVO.setGoodsDTO(mapper.map(goods,GoodsDTO.class));


        return warehouseVO;
    }
    //添加
    public boolean addWarehouse(WarehouseDTO warehouseDTO) {
        return warehouseService.insertSelective(mapper.map(warehouseDTO,Warehouse.class)) > 0 ? true : false;
    }
    //通过主键ID删除
    public boolean deleteWarehouseById(Long warehouseId) {
        return warehouseService.deleteByPrimaryKey(warehouseId) > 0 ? true : false;
    }
    //通过传输一个Warehouse对象修改
    public boolean updateWarehouseById(WarehouseDTO warehouseDTO) {
        return warehouseService.updateByPrimaryKeySelective(mapper.map(warehouseDTO,Warehouse.class)) > 0 ? true : false;
    }

    private List<WarehouseVO> getWarehouseVOList(List<Warehouse> warehouseList) {
        List<WarehouseVO> warehouseVOList = warehouseList.stream()
                .map(cg -> getWarehouseVO(cg))
                .collect(Collectors.toList());
        return warehouseVOList;
    }
    private WarehouseVO getWarehouseVO(Warehouse warehouse) {
        WarehouseVO warehouseVO = mapper.map(warehouse,WarehouseVO.class);
        Cart cart = cartService.selectByPrimaryKey(warehouse.getCartId());
        Goods goods = goodsService.selectByPrimaryKey(warehouse.getGoodsId());
        warehouseVO.setCartDTO(mapper.map(cart,CartDTO.class));
        warehouseVO.setGoodsDTO(mapper.map(goods,GoodsDTO.class));
        return warehouseVO;
    }
}
*/
