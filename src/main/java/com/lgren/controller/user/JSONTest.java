package com.lgren.controller.user;

import com.lgren.pojo.dto.UserDTO;
import com.lgren.pojo.vo.CartGoodsVO;

import java.util.List;
import java.util.Objects;

public class JSONTest {

    /**
     * cartGoodsVOList : [{"cartDTO":{"cartGoods":1,"cartId":1,"userId":1},"cartGoodsId":2,"cartGoodsTime":null,"goodsDTO":{"discount":1,"goodsId":2,"imageUrl":null,"name":"娃哈哈","price":4,"shopId":1,"showTime":null,"type":1},"number":1,"price":3},{"cartDTO":{"cartGoods":1,"cartId":1,"userId":1},"cartGoodsId":11,"cartGoodsTime":null,"goodsDTO":{"discount":0.8,"goodsId":1,"imageUrl":"","name":"可乐","price":3.4,"shopId":1,"showTime":null,"type":1},"number":1,"price":null}]
     * cartId : 1
     * userDTO : {"avatarUrl":null,"birthday":"1983-04-22T00:00:00.000+08:00","email":"73625@qq.com","gender":1,"homeaddress":"四川成都","liveaddress":"广东深圳","money":4000,"nickname":"两个人","password":"user","paymentPassword":"888888","phone":"17263527163","realName":"陆鸿宇","userId":1,"username":"user"}
     */

    private int cartId;
    private UserDTO userDTO;
    private List<CartGoodsVO> cartGoodsVOList;

    public JSONTest() {
    }

    public JSONTest(int cartId, UserDTO userDTO, List<CartGoodsVO> cartGoodsVOList) {

        this.cartId = cartId;
        this.userDTO = userDTO;
        this.cartGoodsVOList = cartGoodsVOList;
    }

    @Override
    public String toString() {
        return "JSONTest{" +
                "cartId=" + cartId +
                ", userDTO=" + userDTO +
                ", cartGoodsVOList=" + cartGoodsVOList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JSONTest jsonTest = (JSONTest) o;
        return cartId == jsonTest.cartId &&
                Objects.equals(userDTO, jsonTest.userDTO) &&
                Objects.equals(cartGoodsVOList, jsonTest.cartGoodsVOList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cartId, userDTO, cartGoodsVOList);
    }

    public int getCartId() {

        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public List<CartGoodsVO> getCartGoodsVOList() {
        return cartGoodsVOList;
    }

    public void setCartGoodsVOList(List<CartGoodsVO> cartGoodsVOList) {
        this.cartGoodsVOList = cartGoodsVOList;
    }
}
