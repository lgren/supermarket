package com.lgren.pojo.vo;

import com.lgren.pojo.dto.ShopDTO;

import java.util.Date;
import java.util.Objects;

public class GoodsVO {
    private Long goodsId;

    private ShopDTO shopDTO;

    private String imageUrl;

    private String name;

    private Integer type;

    private Double price;

    private Double discount;

    private Date showTime;

    public GoodsVO() {
    }

    public GoodsVO(Long goodsId, ShopDTO shopDTO, String imageUrl, String name, Integer type, Double price, Double discount, Date showTime) {

        this.goodsId = goodsId;
        this.shopDTO = shopDTO;
        this.imageUrl = imageUrl;
        this.name = name;
        this.type = type;
        this.price = price;
        this.discount = discount;
        this.showTime = showTime;
    }

    @Override
    public String toString() {
        return "GoodsVO{" +
                "goodsId=" + goodsId +
                ", shopDTO=" + shopDTO +
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
        GoodsVO goodsVO = (GoodsVO) o;
        return Objects.equals(goodsId, goodsVO.goodsId) &&
                Objects.equals(shopDTO, goodsVO.shopDTO) &&
                Objects.equals(imageUrl, goodsVO.imageUrl) &&
                Objects.equals(name, goodsVO.name) &&
                Objects.equals(type, goodsVO.type) &&
                Objects.equals(price, goodsVO.price) &&
                Objects.equals(discount, goodsVO.discount) &&
                Objects.equals(showTime, goodsVO.showTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(goodsId, shopDTO, imageUrl, name, type, price, discount, showTime);
    }

    public Long getGoodsId() {

        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public ShopDTO getShopDTO() {
        return shopDTO;
    }

    public void setShopDTO(ShopDTO shopDTO) {
        this.shopDTO = shopDTO;
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