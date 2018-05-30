package com.lgren.action.user.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Objects;

public class AddGoodsDTO {
    private Long goodsId;

    private Long shopId;

    private String imageUrl;

    private String name;

    private Integer type;

    private Double price;

    private Double discount;

    private Date showTime;

    private Integer number;

    private String authCode;

    private MultipartFile image;

    public AddGoodsDTO() {
    }

    public AddGoodsDTO(Long goodsId, Long shopId, String imageUrl, String name, Integer type, Double price, Double discount, Date showTime, Integer number, String authCode, MultipartFile image) {

        this.goodsId = goodsId;
        this.shopId = shopId;
        this.imageUrl = imageUrl;
        this.name = name;
        this.type = type;
        this.price = price;
        this.discount = discount;
        this.showTime = showTime;
        this.number = number;
        this.authCode = authCode;
        this.image = image;
    }

    @Override
    public String toString() {
        return "AddGoodsDTO{" +
                "goodsId=" + goodsId +
                ", shopId=" + shopId +
                ", imageUrl='" + imageUrl + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", price=" + price +
                ", discount=" + discount +
                ", showTime=" + showTime +
                ", number=" + number +
                ", authCode='" + authCode + '\'' +
                ", image=" + image +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddGoodsDTO that = (AddGoodsDTO) o;
        return Objects.equals(goodsId, that.goodsId) &&
                Objects.equals(shopId, that.shopId) &&
                Objects.equals(imageUrl, that.imageUrl) &&
                Objects.equals(name, that.name) &&
                Objects.equals(type, that.type) &&
                Objects.equals(price, that.price) &&
                Objects.equals(discount, that.discount) &&
                Objects.equals(showTime, that.showTime) &&
                Objects.equals(number, that.number) &&
                Objects.equals(authCode, that.authCode) &&
                Objects.equals(image, that.image);
    }

    @Override
    public int hashCode() {

        return Objects.hash(goodsId, shopId, imageUrl, name, type, price, discount, showTime, number, authCode, image);
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}