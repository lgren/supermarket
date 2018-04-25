package com.lgren.pojo.vo;

import com.lgren.pojo.dto.ShopDTO;
import com.lgren.pojo.po.Goods;

import java.util.List;
import java.util.Objects;

public class WarehouseVO {
    private Long warehouseId;

    private ShopDTO shopDTO;

    private List<Goods> goodsList;

    private Integer number;

    public WarehouseVO() {
    }

    public WarehouseVO(Long warehouseId, ShopDTO shopDTO, List<Goods> goodsList, Integer number) {

        this.warehouseId = warehouseId;
        this.shopDTO = shopDTO;
        this.goodsList = goodsList;
        this.number = number;
    }

    @Override
    public String toString() {
        return "WarehouseVO{" +
                "warehouseId=" + warehouseId +
                ", shopDTO=" + shopDTO +
                ", goodsList=" + goodsList +
                ", number=" + number +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarehouseVO that = (WarehouseVO) o;
        return Objects.equals(warehouseId, that.warehouseId) &&
                Objects.equals(shopDTO, that.shopDTO) &&
                Objects.equals(goodsList, that.goodsList) &&
                Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {

        return Objects.hash(warehouseId, shopDTO, goodsList, number);
    }

    public Long getWarehouseId() {

        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public ShopDTO getShopDTO() {
        return shopDTO;
    }

    public void setShopDTO(ShopDTO shopDTO) {
        this.shopDTO = shopDTO;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}