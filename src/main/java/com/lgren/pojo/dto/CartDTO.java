package com.lgren.pojo.dto;

import java.util.Objects;

public class CartDTO {
    private Long cartId;

    private Long cartGoods;

    private Long userId;

    public CartDTO() {
    }

    public CartDTO(Long cartId, Long cartGoods, Long userId) {

        this.cartId = cartId;
        this.cartGoods = cartGoods;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "CartDTO{" +
                "cartId=" + cartId +
                ", cartGoods=" + cartGoods +
                ", userId=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartDTO cartDTO = (CartDTO) o;
        return Objects.equals(cartId, cartDTO.cartId) &&
                Objects.equals(cartGoods, cartDTO.cartGoods) &&
                Objects.equals(userId, cartDTO.userId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cartId, cartGoods, userId);
    }

    public Long getCartId() {

        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getCartGoods() {
        return cartGoods;
    }

    public void setCartGoods(Long cartGoods) {
        this.cartGoods = cartGoods;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}