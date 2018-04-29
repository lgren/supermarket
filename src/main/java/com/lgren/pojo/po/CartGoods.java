package com.lgren.pojo.po;

import java.util.Date;
import java.util.Objects;

public class CartGoods {
    private Long cartGoodsId;

    private Long cartId;

    private Long goodsId;

    private Date cartGoodsTime;

    private Integer number;

    private Double price;

    private Integer type;

    public CartGoods() {
    }

    public CartGoods(Long cartGoodsId, Long cartId, Long goodsId, Date cartGoodsTime, Integer number, Double price, Integer type) {
        this.cartGoodsId = cartGoodsId;
        this.cartId = cartId;
        this.goodsId = goodsId;
        this.cartGoodsTime = cartGoodsTime;
        this.number = number;
        this.price = price;
        this.type = type;
    }

    @Override
    public String toString() {
        return "CartGoods{" +
                "cartGoodsId=" + cartGoodsId +
                ", cartId=" + cartId +
                ", goodsId=" + goodsId +
                ", cartGoodsTime=" + cartGoodsTime +
                ", number=" + number +
                ", price=" + price +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartGoods cartGoods = (CartGoods) o;
        return Objects.equals(cartGoodsId, cartGoods.cartGoodsId) &&
                Objects.equals(cartId, cartGoods.cartId) &&
                Objects.equals(goodsId, cartGoods.goodsId) &&
                Objects.equals(cartGoodsTime, cartGoods.cartGoodsTime) &&
                Objects.equals(number, cartGoods.number) &&
                Objects.equals(price, cartGoods.price) &&
                Objects.equals(type, cartGoods.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cartGoodsId, cartId, goodsId, cartGoodsTime, number, price, type);
    }

    public Long getCartGoodsId() {

        return cartGoodsId;
    }

    public void setCartGoodsId(Long cartGoodsId) {
        this.cartGoodsId = cartGoodsId;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Date getCartGoodsTime() {
        return cartGoodsTime;
    }

    public void setCartGoodsTime(Date cartGoodsTime) {
        this.cartGoodsTime = cartGoodsTime;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}