package com.lgren.pojo.po;

import java.util.Objects;

public class Collect {
    private Long collectId;

    private Long userId;

    private Long collectGoodsId;

    public Collect() {
    }

    public Collect(Long collectId, Long userId, Long collectGoodsId) {

        this.collectId = collectId;
        this.userId = userId;
        this.collectGoodsId = collectGoodsId;
    }

    @Override
    public String toString() {
        return "Collect{" +
                "collectId=" + collectId +
                ", userId=" + userId +
                ", collectGoodsId=" + collectGoodsId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collect collect = (Collect) o;
        return Objects.equals(collectId, collect.collectId) &&
                Objects.equals(userId, collect.userId) &&
                Objects.equals(collectGoodsId, collect.collectGoodsId);
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