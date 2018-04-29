package com.lgren.api.moudle;

import com.lgren.pojo.dto.CartDTO;
import com.lgren.pojo.dto.UserDTO;
import com.lgren.pojo.po.Cart;
import com.lgren.pojo.vo.CartGoodsVO;
import com.lgren.pojo.vo.CartVO;
import com.lgren.service.CartService;
import com.lgren.service.UserService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CartApi {//OK
    @Autowired
    private Mapper mapper;
    @Autowired
    private CartService cartService;
    @Autowired
    private CartGoodsApi cartGoodsApi;
    @Autowired
    private UserService userService;
    //查询所有
    public List<CartVO> getAllCart() {
        List<Cart> cartList = cartService.selectAll();
        List<CartVO> cartVOList = cartList.stream()
                .map(cart -> getCartVO(cart,0))
                .collect(Collectors.toList());
        return cartVOList;
    }
    //根据用户主键ID查询
    public CartVO getCartByUserId(Long userId) {
        Cart cart = cartService.getCartByUserId(userId);
        return getCartVO(cart,null);
    }
    //根据用户主键ID查询
    public CartVO getCartGoodsByUserIdAndType(Long userId,Integer type) {
        Cart cart = cartService.getCartByUserId(userId);
        return getCartVO(cart,type);
    }
    //根据主键ID查询
    public CartVO getCartById(Long cartId) {
        Cart cart = cartService.selectByPrimaryKey(cartId);
        return getCartVO(cart,null);
    }
    //添加
    public boolean addCart(CartDTO cartDTO) {
        return cartService.insertSelective(mapper.map(cartDTO,Cart.class)) > 0 ? true : false;
    }
    //通过主键ID删除
    public boolean deleteCartById(Long cartId) {
        return cartService.deleteByPrimaryKey(cartId) > 0 ? true : false;
    }
    //通过传输一个Cart对象修改
    public boolean updateCartById(CartDTO cartDTO) {
        return cartService.updateByPrimaryKeySelective(mapper.map(cartDTO,Cart.class)) > 0 ? true : false;
    }


    private CartVO getCartVO(Cart cart,Integer type) {
        CartVO cartVO = mapper.map(cart,CartVO.class);
        UserDTO userDTO = mapper.map(userService.selectByPrimaryKey(cart.getUserId()),UserDTO.class);
        List<CartGoodsVO> cartGoodsVOList = cartGoodsApi.getCartGoodsByCartIdAndType(cart.getCartId(),type);
        cartVO.setUserDTO(userDTO);
        cartVO.setCartGoodsVOList(cartGoodsVOList);
        return cartVO;
    }



}
