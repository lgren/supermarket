package com.lgren.pojo.po;

import java.util.Objects;

public class Cart {
    private Long cartId;

    private Long cartGoods;

    private Long userId;

    public Cart() {
    }

    public Cart(Long cartId, Long cartGoods, Long userId) {

        this.cartId = cartId;
        this.cartGoods = cartGoods;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", cartGoods=" + cartGoods +
                ", userId=" + userId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(cartId, cart.cartId) &&
                Objects.equals(cartGoods, cart.cartGoods) &&
                Objects.equals(userId, cart.userId);
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