package com.lgren.pojo.dto;

import java.util.Date;
import java.util.Objects;

public class CartGoodsDTO {
    private Long cartGoodsId;

    private Long cartId;

    private Long goodsId;

    private Date cartGoodsTime;

    private Integer number;

    private Double price;

    public CartGoodsDTO() {
    }

    public CartGoodsDTO(Long cartGoodsId, Long cartId, Long goodsId, Date cartGoodsTime, Integer number, Double price) {

        this.cartGoodsId = cartGoodsId;
        this.cartId = cartId;
        this.goodsId = goodsId;
        this.cartGoodsTime = cartGoodsTime;
        this.number = number;
        this.price = price;
    }

    @Override
    public String toString() {
        return "CartGoodsDTO{" +
                "cartGoodsId=" + cartGoodsId +
                ", cartId=" + cartId +
                ", goodsId=" + goodsId +
                ", cartGoodsTime=" + cartGoodsTime +
                ", number=" + number +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartGoodsDTO that = (CartGoodsDTO) o;
        return Objects.equals(cartGoodsId, that.cartGoodsId) &&
                Objects.equals(cartId, that.cartId) &&
                Objects.equals(goodsId, that.goodsId) &&
                Objects.equals(cartGoodsTime, that.cartGoodsTime) &&
                Objects.equals(number, that.number) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cartGoodsId, cartId, goodsId, cartGoodsTime, number, price);
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
}