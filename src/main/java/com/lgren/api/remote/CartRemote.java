package com.lgren.api.remote;

import com.lgren.pojo.po.Cart;
import com.lgren.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/remote")
public class CartRemote {
    @Autowired
    private CartService cartService;
    //查询所有
    @GetMapping(value = "/cart.do")
    public List<Cart> getAllCart() {
        return cartService.selectAll();
    }
    //根据主键ID查询
    @GetMapping(value = "/cart.do/{cartId}")
    public Cart getCartById(@PathVariable("cartId") Long cartId) {
        return cartService.selectByPrimaryKey(cartId);
    }
    //根据用户主键ID查询
    public Cart getCartByUserId(Long userId) {
        return cartService.getCartByUserId(userId);
    }
    //添加
    @PostMapping(value = "/cart.do")
    public boolean addCart(Cart cart) {
        return cartService.insertSelective(cart) > 0 ? true : false;
    }
    //通过主键ID删除
    @DeleteMapping(value = "/cart.do/{cartId}")
    public boolean deleteCartById(@PathVariable("cartId") Long cartId) {
        return cartService.deleteByPrimaryKey(cartId) > 0 ? true : false;
    }
    //通过传输一个Cart对象修改
    @PutMapping(value = "/cart.do")
    public boolean updateCartById(Cart cart) {
        return cartService.updateByPrimaryKeySelective(cart) > 0 ? true : false;
    }



}
