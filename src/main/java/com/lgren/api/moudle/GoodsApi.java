package com.lgren.api.moudle;

import com.lgren.pojo.dto.GoodsDTO;
import com.lgren.pojo.dto.ShopDTO;
import com.lgren.pojo.po.Goods;
import com.lgren.pojo.po.Shop;
import com.lgren.pojo.vo.GoodsVO;
import com.lgren.service.GoodsService;
import com.lgren.service.ShopService;
import com.lgren.service.WarehouseService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class GoodsApi {//OK
    @Autowired
    private Mapper mapper;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private WarehouseService warehouseService;
    //查询所有
    public List<GoodsVO> getAllGoods() {
        return goodsService.selectAll().stream().map(goods -> getGoodsVO(goods)).collect(Collectors.toList());
    }
    //查询所有
    public List<GoodsVO> getGoodsVOList(List<Goods> goodsList) {
        return goodsList.stream().map(goods -> getGoodsVO(goods)).collect(Collectors.toList());
    }
    //根据主键ID查询
    public GoodsVO getGoodsById(Long goodsId) {
        Goods goods = goodsService.selectByPrimaryKey(goodsId);
        return getGoodsVO(goods);
    }
    //添加
    public boolean addGoods(GoodsDTO goodsDTO) {
        return goodsService.insertSelective(mapper.map(goodsDTO,Goods.class)) > 0 ? true : false;
    }
    //通过主键ID删除
    public boolean deleteGoodsById(Long goodsId) {
        return goodsService.deleteByPrimaryKey(goodsId) > 0 ? true : false;
    }
    //通过传输一个Goods对象修改
    public boolean updateGoodsById(GoodsDTO goodsDTO) {
        return goodsService.updateByPrimaryKeySelective(mapper.map(goodsDTO,Goods.class)) > 0 ? true : false;
    }
    public GoodsVO getGoodsVO(Goods goods) {
        if (goods == null) {
            return null;
        }
        Shop shop = shopService.selectByPrimaryKey(goods.getShopId());
        GoodsVO goodsVO = mapper.map(goods,GoodsVO.class);
        if (shop != null) {
            goodsVO.setShopDTO(mapper.map(shop,ShopDTO.class));
        }
        goodsVO.setDiscount(goodsVO.getDiscount()*10);
        goodsVO.setNumber(warehouseService.getWarehouseByShopIdAndGoodsId(shop.getShopId(),goods.getGoodsId()).getNumber());
        return goodsVO;
    }
}
