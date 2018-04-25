package com.lgren.pojo.vo;

import com.lgren.pojo.dto.UserDTO;

import java.util.List;
import java.util.Objects;

public class CartVO {
    private Long cartId;

    private List<CartGoodsVO> cartGoodsVOList;

    private UserDTO userDTO;

    public CartVO() {
    }

    public CartVO(Long cartId, List<CartGoodsVO> cartGoodsVOList, UserDTO userDTO) {

        this.cartId = cartId;
        this.cartGoodsVOList = cartGoodsVOList;
        this.userDTO = userDTO;
    }

    @Override
    public String toString() {
        return "CartVO{" +
                "cartId=" + cartId +
                ", cartGoodsVOList=" + cartGoodsVOList +
                ", userDTO=" + userDTO +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartVO cartVO = (CartVO) o;
        return Objects.equals(cartId, cartVO.cartId) &&
                Objects.equals(cartGoodsVOList, cartVO.cartGoodsVOList) &&
                Objects.equals(userDTO, cartVO.userDTO);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cartId, cartGoodsVOList, userDTO);
    }

    public Long getCartId() {

        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public List<CartGoodsVO> getCartGoodsVOList() {
        return cartGoodsVOList;
    }

    public void setCartGoodsVOList(List<CartGoodsVO> cartGoodsVOList) {
        this.cartGoodsVOList = cartGoodsVOList;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}