package com.lgren.api.remote;

import com.lgren.pojo.po.Goods;
import com.lgren.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/remote")
public class GoodsRemote {
    @Autowired
    private GoodsService goodsService;
    //查询所有
    @RequestMapping(value = "/goods.do",method = RequestMethod.GET)
    public List<Goods> getAllGoods() {
        return goodsService.selectAll();
    }
    //根据主键ID查询
    @RequestMapping(value = "/goods.do/{goodsId}",method = RequestMethod.GET)
    public Goods getGoodsById(@PathVariable("goodsId") Long goodsId) {
        return goodsService.selectByPrimaryKey(goodsId);
    }
    //添加
    @RequestMapping(value = "/goods.do",method = RequestMethod.POST)
    public boolean addGoods(Goods goods) {
        return goodsService.insertSelective(goods) > 0 ? true : false;
    }
    //通过主键ID删除
    @RequestMapping(value = "/goods.do/{goodsId}",method = RequestMethod.DELETE)
    public boolean deleteGoodsById(@PathVariable("goodsId") Long goodsId) {
        return goodsService.deleteByPrimaryKey(goodsId) > 0 ? true : false;
    }
    //通过传输一个Goods对象修改
    @RequestMapping(value = "/goods.do",method = RequestMethod.PUT)
    public boolean updateGoodsById(Goods goods) {
        return goodsService.updateByPrimaryKeySelective(goods) > 0 ? true : false;
    }



}
