package com.lgren.pojo.po;

import java.util.Date;
import java.util.Objects;

public class Goods {
    private Long goodsId;

    private Long shopId;

    private String imageUrl;

    private String name;

    private Integer type;

    private Double price;

    private Double discount;

    private Date showTime;

    public Goods() {
    }

    public Goods(Long goodsId, Long shopId, String imageUrl, String name, Integer type, Double price, Double discount, Date showTime) {

        this.goodsId = goodsId;
        this.shopId = shopId;
        this.imageUrl = imageUrl;
        this.name = name;
        this.type = type;
        this.price = price;
        this.discount = discount;
        this.showTime = showTime;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodsId=" + goodsId +
                ", shopId=" + shopId +
                ", imageUrl='" + imageUrl + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", price=" + price +
                ", discount=" + discount +
                ", showTime=" + showTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return Objects.equals(goodsId, goods.goodsId) &&
                Objects.equals(shopId, goods.shopId) &&
                Objects.equals(imageUrl, goods.imageUrl) &&
                Objects.equals(name, goods.name) &&
                Objects.equals(type, goods.type) &&
                Objects.equals(price, goods.price) &&
                Objects.equals(discount, goods.discount) &&
                Objects.equals(showTime, goods.showTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(goodsId, shopId, imageUrl, name, type, price, discount, showTime);
    }

    public Long getGoodsId() {

        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Date getShowTime() {
        return showTime;
    }

    public void setShowTime(Date showTime) {
        this.showTime = showTime;
    }
}