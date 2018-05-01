package com.lgren.pojo.vo;

import com.lgren.pojo.dto.CartDTO;
import com.lgren.pojo.dto.GoodsDTO;

import java.util.Date;
import java.util.Objects;

public class CartGoodsVO {
    private Long cartGoodsId;

    private CartDTO cartDTO;

    private GoodsDTO goodsDTO;

    private Date cartGoodsTime;

    private Integer number;

    private Double price;

    private Integer type;

    private Long wantPayTime;

    public CartGoodsVO() {
    }

    public CartGoodsVO(Long cartGoodsId, CartDTO cartDTO, GoodsDTO goodsDTO, Date cartGoodsTime, Integer number, Double price, Integer type, Long wantPayTime) {

        this.cartGoodsId = cartGoodsId;
        this.cartDTO = cartDTO;
        this.goodsDTO = goodsDTO;
        this.cartGoodsTime = cartGoodsTime;
        this.number = number;
        this.price = price;
        this.type = type;
        this.wantPayTime = wantPayTime;
    }

    @Override
    public String toString() {
        return "CartGoodsVO{" +
                "cartGoodsId=" + cartGoodsId +
                ", cartDTO=" + cartDTO +
                ", goodsDTO=" + goodsDTO +
                ", cartGoodsTime=" + cartGoodsTime +
                ", number=" + number +
                ", price=" + price +
                ", type=" + type +
                ", wantPayTime=" + wantPayTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartGoodsVO that = (CartGoodsVO) o;
        return Objects.equals(cartGoodsId, that.cartGoodsId) &&
                Objects.equals(cartDTO, that.cartDTO) &&
                Objects.equals(goodsDTO, that.goodsDTO) &&
                Objects.equals(cartGoodsTime, that.cartGoodsTime) &&
                Objects.equals(number, that.number) &&
                Objects.equals(price, that.price) &&
                Objects.equals(type, that.type) &&
                Objects.equals(wantPayTime, that.wantPayTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cartGoodsId, cartDTO, goodsDTO, cartGoodsTime, number, price, type, wantPayTime);
    }

    public Long getCartGoodsId() {

        return cartGoodsId;
    }

    public void setCartGoodsId(Long cartGoodsId) {
        this.cartGoodsId = cartGoodsId;
    }

    public CartDTO getCartDTO() {
        return cartDTO;
    }

    public void setCartDTO(CartDTO cartDTO) {
        this.cartDTO = cartDTO;
    }

    public GoodsDTO getGoodsDTO() {
        return goodsDTO;
    }

    public void setGoodsDTO(GoodsDTO goodsDTO) {
        this.goodsDTO = goodsDTO;
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

    public Long getWantPayTime() {
        return wantPayTime;
    }

    public void setWantPayTime(Long wantPayTime) {
        this.wantPayTime = wantPayTime;
    }
}