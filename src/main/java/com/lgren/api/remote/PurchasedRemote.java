package com.lgren.api.remote;

import com.lgren.pojo.po.Purchased;
import com.lgren.service.PurchasedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/remote")
public class PurchasedRemote {
    @Autowired
    private PurchasedService purchasedService;
    //查询所有
    @RequestMapping(value = "/purchased.do",method = RequestMethod.GET)
    public List<Purchased> getAllPurchased() {
        return purchasedService.selectAll();
    }
    //根据主键ID查询
    @RequestMapping(value = "/purchased.do/{purchasedId}",method = RequestMethod.GET)
    public Purchased getPurchasedById(@PathVariable("purchasedId") Long purchasedId) {
        return purchasedService.selectByPrimaryKey(purchasedId);
    }
    //添加
    @RequestMapping(value = "/purchased.do",method = RequestMethod.POST)
    public boolean addPurchased(Purchased purchased) {
        return purchasedService.insertSelective(purchased) > 0 ? true : false;
    }
    //通过主键ID删除
    @RequestMapping(value = "/purchased.do/{purchasedId}",method = RequestMethod.DELETE)
    public boolean deletePurchasedById(@PathVariable("purchasedId") Long purchasedId) {
        return purchasedService.deleteByPrimaryKey(purchasedId) > 0 ? true : false;
    }
    //通过传输一个Purchased对象修改
    @RequestMapping(value = "/purchased.do",method = RequestMethod.PUT)
    public boolean updatePurchasedById(Purchased purchased) {
        return purchasedService.updateByPrimaryKeySelective(purchased) > 0 ? true : false;
    }



}
