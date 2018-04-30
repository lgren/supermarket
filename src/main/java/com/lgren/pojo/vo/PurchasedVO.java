package com.lgren.pojo.vo;

import java.util.Date;
import java.util.Objects;

public class PurchasedVO {
    private Long purchasedId;

    private OrderVO orderVO;

    private Date time;

    private Integer evaluation;

    private String evaluationText;

    private Date evaluationTime;

    private Integer state;

    public PurchasedVO() {
    }

    public PurchasedVO(Long purchasedId, OrderVO orderVO, Date time, Integer evaluation, String evaluationText, Date evaluationTime, Integer state) {

        this.purchasedId = purchasedId;
        this.orderVO = orderVO;
        this.time = time;
        this.evaluation = evaluation;
        this.evaluationText = evaluationText;
        this.evaluationTime = evaluationTime;
        this.state = state;
    }

    @Override
    public String toString() {
        return "PurchasedVO{" +
                "purchasedId=" + purchasedId +
                ", orderVO=" + orderVO +
                ", time=" + time +
                ", evaluation=" + evaluation +
                ", evaluationText='" + evaluationText + '\'' +
                ", evaluationTime=" + evaluationTime +
                ", state=" + state +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchasedVO that = (PurchasedVO) o;
        return Objects.equals(purchasedId, that.purchasedId) &&
                Objects.equals(orderVO, that.orderVO) &&
                Objects.equals(time, that.time) &&
                Objects.equals(evaluation, that.evaluation) &&
                Objects.equals(evaluationText, that.evaluationText) &&
                Objects.equals(evaluationTime, that.evaluationTime) &&
                Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {

        return Objects.hash(purchasedId, orderVO, time, evaluation, evaluationText, evaluationTime, state);
    }

    public Long getPurchasedId() {

        return purchasedId;
    }

    public void setPurchasedId(Long purchasedId) {
        this.purchasedId = purchasedId;
    }

    public OrderVO getOrderVO() {
        return orderVO;
    }

    public void setOrderVO(OrderVO orderVO) {
        this.orderVO = orderVO;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Integer evaluation) {
        this.evaluation = evaluation;
    }

    public String getEvaluationText() {
        return evaluationText;
    }

    public void setEvaluationText(String evaluationText) {
        this.evaluationText = evaluationText;
    }

    public Date getEvaluationTime() {
        return evaluationTime;
    }

    public void setEvaluationTime(Date evaluationTime) {
        this.evaluationTime = evaluationTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}