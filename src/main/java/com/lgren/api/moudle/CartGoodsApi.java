package com.lgren.api.moudle;

import com.lgren.pojo.dto.CartDTO;
import com.lgren.pojo.dto.CartGoodsDTO;
import com.lgren.pojo.dto.GoodsDTO;
import com.lgren.pojo.po.Cart;
import com.lgren.pojo.po.CartGoods;
import com.lgren.pojo.po.Goods;
import com.lgren.pojo.vo.CartGoodsVO;
import com.lgren.service.CartGoodsService;
import com.lgren.service.CartService;
import com.lgren.service.GoodsService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CartGoodsApi {//OK
    @Autowired
    private Mapper mapper;
    @Autowired
    private CartGoodsService cartGoodsService;
    @Autowired
    private CartService cartService;
    @Autowired
    private GoodsService goodsService;
    //查询所有
    public List<CartGoodsVO> getAllCartGoods() {
        List<CartGoods> cartGoodsList = cartGoodsService.selectAll();
        List<CartGoodsVO> cartGoodsVOList = getCartGoodsVOList(cartGoodsList);
        return cartGoodsVOList;
    }
    public List<CartGoodsVO> getCartGoodsByCartId(Long cartId) {
        List<CartGoods> cartGoodsList = cartGoodsService.getCartGoodsByCartId(cartId);
        List<CartGoodsVO> cartGoodsVOList = getCartGoodsVOList(cartGoodsList);
        return cartGoodsVOList;
    }
    public List<CartGoodsVO> getCartGoodsByGoodsId(Long goodsId) {
        List<CartGoods> cartGoodsList = cartGoodsService.getCartGoodsByCartId(goodsId);
        List<CartGoodsVO> cartGoodsVOList = getCartGoodsVOList(cartGoodsList);
        return cartGoodsVOList;
    }
    //根据主键ID查询
    public CartGoodsVO getCartGoodsById(Long cartGoodsId) {
        CartGoods cartGoods = cartGoodsService.selectByPrimaryKey(cartGoodsId);
        CartGoodsVO cartGoodsVO = mapper.map(cartGoods,CartGoodsVO.class);

        Cart cart = cartService.selectByPrimaryKey(cartGoods.getCartId());
        Goods goods = goodsService.selectByPrimaryKey(cartGoods.getGoodsId());
        cartGoodsVO.setCartDTO(mapper.map(cart,CartDTO.class));
        cartGoodsVO.setGoodsDTO(mapper.map(goods,GoodsDTO.class));


        return cartGoodsVO;
    }
    //添加
    public boolean addCartGoods(CartGoodsDTO cartGoodsDTO) {
        return cartGoodsService.insertSelective(mapper.map(cartGoodsDTO,CartGoods.class)) > 0 ? true : false;
    }
    //通过主键ID删除
    public boolean deleteCartGoodsById(Long cartGoodsId) {
        return cartGoodsService.deleteByPrimaryKey(cartGoodsId) > 0 ? true : false;
    }
    //通过传输一个CartGoods对象修改
    public boolean updateCartGoodsById(CartGoodsDTO cartGoodsDTO) {
        return cartGoodsService.updateByPrimaryKeySelective(mapper.map(cartGoodsDTO,CartGoods.class)) > 0 ? true : false;
    }



    private List<CartGoodsVO> getCartGoodsVOList(List<CartGoods> cartGoodsList) {
        List<CartGoodsVO> cartGoodsVOList = cartGoodsList.stream()
                .map(cg -> getCartGoodsVO(cg))
                .collect(Collectors.toList());
        return cartGoodsVOList;
    }
    private CartGoodsVO getCartGoodsVO(CartGoods cartGoods) {
        CartGoodsVO cartGoodsVO = mapper.map(cartGoods,CartGoodsVO.class);
        Cart cart = cartService.selectByPrimaryKey(cartGoods.getCartId());
        Goods goods = goodsService.selectByPrimaryKey(cartGoods.getGoodsId());
        cartGoodsVO.setCartDTO(mapper.map(cart,CartDTO.class));
        cartGoodsVO.setGoodsDTO(mapper.map(goods,GoodsDTO.class));
        return cartGoodsVO;
    }
}
