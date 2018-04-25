package com.lgren.api.remote;

import com.lgren.pojo.po.Shop;
import com.lgren.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/remote")
public class ShopRemote {
    @Autowired
    private ShopService shopService;
    //查询所有
    @RequestMapping(value = "/shop.do",method = RequestMethod.GET)
    public List<Shop> getAllShop() {
        return shopService.selectAll();
    }
    //根据主键ID查询
    @RequestMapping(value = "/shop.do/{shopId}",method = RequestMethod.GET)
    public Shop getShopById(@PathVariable("shopId") Long shopId) {
        return shopService.selectByPrimaryKey(shopId);
    }
    //添加
    @RequestMapping(value = "/shop.do",method = RequestMethod.POST)
    public boolean addShop(Shop shop) {
        return shopService.insertSelective(shop) > 0 ? true : false;
    }
    //通过主键ID删除
    @RequestMapping(value = "/shop.do/{shopId}",method = RequestMethod.DELETE)
    public boolean deleteShopById(@PathVariable("shopId") Long shopId) {
        return shopService.deleteByPrimaryKey(shopId) > 0 ? true : false;
    }
    //通过传输一个Shop对象修改
    @RequestMapping(value = "/shop.do",method = RequestMethod.PUT)
    public boolean updateShopById(Shop shop) {
        return shopService.updateByPrimaryKeySelective(shop) > 0 ? true : false;
    }



}
