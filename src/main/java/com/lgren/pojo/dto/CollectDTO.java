package com.lgren.pojo.dto;

import java.util.Objects;

public class CollectDTO {
    private Long collectId;

    private Long userId;

    private Long collectGoodsId;

    public CollectDTO() {
    }

    public CollectDTO(Long collectId, Long userId, Long collectGoodsId) {

        this.collectId = collectId;
        this.userId = userId;
        this.collectGoodsId = collectGoodsId;
    }

    @Override
    public String toString() {
        return "CollectDTO{" +
                "collectId=" + collectId +
                ", userId=" + userId +
                ", collectGoodsId=" + collectGoodsId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollectDTO that = (CollectDTO) o;
        return Objects.equals(collectId, that.collectId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(collectGoodsId, that.collectGoodsId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(collectId, userId, collectGoodsId);
    }

    public Long getCollectId() {

        return collectId;
    }

    public void setCollectId(Long collectId) {
        this.collectId = collectId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCollectGoodsId() {
        return collectGoodsId;
    }

    public void setCollectGoodsId(Long collectGoodsId) {
        this.collectGoodsId = collectGoodsId;
    }
}