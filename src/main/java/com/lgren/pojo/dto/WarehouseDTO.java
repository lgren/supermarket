package com.lgren.pojo.dto;

import java.util.Objects;

public class WarehouseDTO {
    private Long warehouseId;

    private Long shopId;

    private Long goodsId;

    private Integer number;

    public WarehouseDTO() {
    }

    public WarehouseDTO(Long warehouseId, Long shopId, Long goodsId, Integer number) {

        this.warehouseId = warehouseId;
        this.shopId = shopId;
        this.goodsId = goodsId;
        this.number = number;
    }

    @Override
    public String toString() {
        return "WarehouseDTO{" +
                "warehouseId=" + warehouseId +
                ", shopId=" + shopId +
                ", goodsId=" + goodsId +
                ", number=" + number +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarehouseDTO warehouse = (WarehouseDTO) o;
        return Objects.equals(warehouseId, warehouse.warehouseId) &&
                Objects.equals(shopId, warehouse.shopId) &&
                Objects.equals(goodsId, warehouse.goodsId) &&
                Objects.equals(number, warehouse.number);
    }

    @Override
    public int hashCode() {

        return Objects.hash(warehouseId, shopId, goodsId, number);
    }

    public Long getWarehouseId() {

        return warehouseId;
    }

    public void setWarehouseId(Long warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
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
}