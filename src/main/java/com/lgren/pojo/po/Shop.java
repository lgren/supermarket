package com.lgren.pojo.po;

import java.util.Date;
import java.util.Objects;

public class Shop {
    private Long shopId;

    private Long userId;

    private Long warehousrId;

    private String name;

    private String description;

    private Date showTime;

    private Double money;

    private Integer state;

    public Shop() {
    }

    public Shop(Long shopId, Long userId, Long warehousrId, String name, String description, Date showTime, Double money, Integer state) {

        this.shopId = shopId;
        this.userId = userId;
        this.warehousrId = warehousrId;
        this.name = name;
        this.description = description;
        this.showTime = showTime;
        this.money = money;
        this.state = state;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "shopId=" + shopId +
                ", userId=" + userId +
                ", warehousrId=" + warehousrId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", showTime=" + showTime +
                ", money=" + money +
                ", state=" + state +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop shop = (Shop) o;
        return Objects.equals(shopId, shop.shopId) &&
                Objects.equals(userId, shop.userId) &&
                Objects.equals(warehousrId, shop.warehousrId) &&
                Objects.equals(name, shop.name) &&
                Objects.equals(description, shop.description) &&
                Objects.equals(showTime, shop.showTime) &&
                Objects.equals(money, shop.money) &&
                Objects.equals(state, shop.state);
    }

    @Override
    public int hashCode() {

        return Objects.hash(shopId, userId, warehousrId, name, description, showTime, money, state);
    }

    public Long getShopId() {

        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getWarehousrId() {
        return warehousrId;
    }

    public void setWarehousrId(Long warehousrId) {
        this.warehousrId = warehousrId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getShowTime() {
        return showTime;
    }

    public void setShowTime(Date showTime) {
        this.showTime = showTime;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}