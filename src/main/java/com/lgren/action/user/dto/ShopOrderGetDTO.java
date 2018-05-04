package com.lgren.action.user.dto;

import java.util.Objects;

public class ShopOrderGetDTO {
    private Long userId;
    private Long goodsId;
    private Integer number;
    private Double money;

    public ShopOrderGetDTO() {
    }

    public ShopOrderGetDTO(Long userId, Long goodsId, Integer number, Double money) {
        this.userId = userId;
        this.goodsId = goodsId;
        this.number = number;
        this.money = money;
    }

    @Override
    public String toString() {
        return "ShopOrderGetDTO{" +
                "userId=" + userId +
                ", goodsId=" + goodsId +
                ", number=" + number +
                ", money=" + money +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopOrderGetDTO that = (ShopOrderGetDTO) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(goodsId, that.goodsId) &&
                Objects.equals(number, that.number) &&
                Objects.equals(money, that.money);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, goodsId, number, money);
    }

    public Long getUserId() {

        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
