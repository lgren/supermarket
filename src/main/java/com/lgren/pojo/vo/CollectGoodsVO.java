package com.lgren.pojo.vo;

import com.lgren.pojo.dto.CollectDTO;
import com.lgren.pojo.dto.GoodsDTO;

import java.util.Date;
import java.util.Objects;

public class CollectGoodsVO {
    private Long collectGoodsId;

    private CollectDTO collectDTO;

    private GoodsDTO goodsDTO;

    private Date collectGoodsTime;

    public CollectGoodsVO() {
    }

    public CollectGoodsVO(Long collectGoodsId, CollectDTO collectDTO, GoodsDTO goodsDTO, Date collectGoodsTime) {

        this.collectGoodsId = collectGoodsId;
        this.collectDTO = collectDTO;
        this.goodsDTO = goodsDTO;
        this.collectGoodsTime = collectGoodsTime;
    }

    @Override
    public String toString() {
        return "CollectGoodsVO{" +
                "collectGoodsId=" + collectGoodsId +
                ", collectDTO=" + collectDTO +
                ", goodsDTO=" + goodsDTO +
                ", collectGoodsTime=" + collectGoodsTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollectGoodsVO that = (CollectGoodsVO) o;
        return Objects.equals(collectGoodsId, that.collectGoodsId) &&
                Objects.equals(collectDTO, that.collectDTO) &&
                Objects.equals(goodsDTO, that.goodsDTO) &&
                Objects.equals(collectGoodsTime, that.collectGoodsTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(collectGoodsId, collectDTO, goodsDTO, collectGoodsTime);
    }

    public Long getCollectGoodsId() {

        return collectGoodsId;
    }

    public void setCollectGoodsId(Long collectGoodsId) {
        this.collectGoodsId = collectGoodsId;
    }

    public CollectDTO getCollectDTO() {
        return collectDTO;
    }

    public void setCollectDTO(CollectDTO collectDTO) {
        this.collectDTO = collectDTO;
    }

    public GoodsDTO getGoodsDTO() {
        return goodsDTO;
    }

    public void setGoodsDTO(GoodsDTO goodsDTO) {
        this.goodsDTO = goodsDTO;
    }

    public Date getCollectGoodsTime() {
        return collectGoodsTime;
    }

    public void setCollectGoodsTime(Date collectGoodsTime) {
        this.collectGoodsTime = collectGoodsTime;
    }
}