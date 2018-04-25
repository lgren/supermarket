package com.lgren.pojo.po;

import java.util.Date;
import java.util.Objects;

public class CollectGoods {
    private Long collectGoodsId;

    private Long collectId;

    private Long goodsId;

    private Date collectGoodsTime;

    public CollectGoods() {
    }

    public CollectGoods(Long collectGoodsId, Long collectId, Long goodsId, Date collectGoodsTime) {

        this.collectGoodsId = collectGoodsId;
        this.collectId = collectId;
        this.goodsId = goodsId;
        this.collectGoodsTime = collectGoodsTime;
    }

    @Override
    public String toString() {
        return "CollectGoods{" +
                "collectGoodsId=" + collectGoodsId +
                ", collectId=" + collectId +
                ", goodsId=" + goodsId +
                ", collectGoodsTime=" + collectGoodsTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollectGoods that = (CollectGoods) o;
        return Objects.equals(collectGoodsId, that.collectGoodsId) &&
                Objects.equals(collectId, that.collectId) &&
                Objects.equals(goodsId, that.goodsId) &&
                Objects.equals(collectGoodsTime, that.collectGoodsTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(collectGoodsId, collectId, goodsId, collectGoodsTime);
    }

    public Long getCollectGoodsId() {

        return collectGoodsId;
    }

    public void setCollectGoodsId(Long collectGoodsId) {
        this.collectGoodsId = collectGoodsId;
    }

    public Long getCollectId() {
        return collectId;
    }

    public void setCollectId(Long collectId) {
        this.collectId = collectId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Date getCollectGoodsTime() {
        return collectGoodsTime;
    }

    public void setCollectGoodsTime(Date collectGoodsTime) {
        this.collectGoodsTime = collectGoodsTime;
    }
}