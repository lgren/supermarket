package com.lgren.api.remote;

import com.lgren.pojo.po.CollectGoods;
import com.lgren.service.CollectGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/remote")
public class CollectGoodsRemote {
    @Autowired
    private CollectGoodsService collectGoodsService;
    //查询所有
    @RequestMapping(value = "/collectGoods.do",method = RequestMethod.GET)
    public List<CollectGoods> getAllCollectGoods() {
        return collectGoodsService.selectAll();
    }
    //根据主键ID查询
    @RequestMapping(value = "/collectGoods.do/{collectGoodsId}",method = RequestMethod.GET)
    public CollectGoods getCollectGoodsById(@PathVariable("collectGoodsId") Long collectGoodsId) {
        return collectGoodsService.selectByPrimaryKey(collectGoodsId);
    }
    //添加
    @RequestMapping(value = "/collectGoods.do",method = RequestMethod.POST)
    public boolean addCollectGoods(CollectGoods collectGoods) {
        return collectGoodsService.insertSelective(collectGoods) > 0 ? true : false;
    }
    //通过主键ID删除
    @RequestMapping(value = "/collectGoods.do/{collectGoodsId}",method = RequestMethod.DELETE)
    public boolean deleteCollectGoodsById(@PathVariable("collectGoodsId") Long collectGoodsId) {
        return collectGoodsService.deleteByPrimaryKey(collectGoodsId) > 0 ? true : false;
    }
    //通过传输一个CollectGoods对象修改
    @RequestMapping(value = "/collectGoods.do",method = RequestMethod.PUT)
    public boolean updateCollectGoodsById(CollectGoods collectGoods) {
        return collectGoodsService.updateByPrimaryKeySelective(collectGoods) > 0 ? true : false;
    }



}
