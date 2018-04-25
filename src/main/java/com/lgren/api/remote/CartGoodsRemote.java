package com.lgren.api.remote;

import com.lgren.pojo.po.CartGoods;
import com.lgren.service.CartGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/remote")
public class CartGoodsRemote {
    @Autowired
    private CartGoodsService cartGoodsService;
    //查询所有
    @RequestMapping(value = "/cartGoods.do",method = RequestMethod.GET)
    public List<CartGoods> getAllCartGoods() {
        return cartGoodsService.selectAll();
    }
    //根据主键ID查询
    @RequestMapping(value = "/cartGoods.do/{cartGoodsId}",method = RequestMethod.GET)
    public CartGoods getCartGoodsById(@PathVariable("cartGoodsId") Long cartGoodsId) {
        return cartGoodsService.selectByPrimaryKey(cartGoodsId);
    }
    //添加
    @RequestMapping(value = "/cartGoods.do",method = RequestMethod.POST)
    public boolean addCartGoods(CartGoods cartGoods) {
        return cartGoodsService.insertSelective(cartGoods) > 0 ? true : false;
    }
    //通过主键ID删除
    @RequestMapping(value = "/cartGoods.do/{cartGoodsId}",method = RequestMethod.DELETE)
    public boolean deleteCartGoodsById(@PathVariable("cartGoodsId") Long cartGoodsId) {
        return cartGoodsService.deleteByPrimaryKey(cartGoodsId) > 0 ? true : false;
    }
    //通过传输一个CartGoods对象修改
    @RequestMapping(value = "/cartGoods.do",method = RequestMethod.PUT)
    public boolean updateCartGoodsById(CartGoods cartGoods) {
        return cartGoodsService.updateByPrimaryKeySelective(cartGoods) > 0 ? true : false;
    }



}
