package com.lgren.pojo.po;

import java.util.Date;
import java.util.Objects;

public class Purchased {
    private Long purchasedId;

    private Long orderId;

    private Date time;

    private Integer evaluation;

    private String evaluationText;

    private Date evaluationTime;

    private Integer state;

    public Purchased() {
    }

    public Purchased(Long purchasedId, Long orderId, Date time, Integer evaluation, String evaluationText, Date evaluationTime, Integer state) {

        this.purchasedId = purchasedId;
        this.orderId = orderId;
        this.time = time;
        this.evaluation = evaluation;
        this.evaluationText = evaluationText;
        this.evaluationTime = evaluationTime;
        this.state = state;
    }

    @Override
    public String toString() {
        return "Purchased{" +
                "purchasedId=" + purchasedId +
                ", orderId=" + orderId +
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
        Purchased purchased = (Purchased) o;
        return Objects.equals(purchasedId, purchased.purchasedId) &&
                Objects.equals(orderId, purchased.orderId) &&
                Objects.equals(time, purchased.time) &&
                Objects.equals(evaluation, purchased.evaluation) &&
                Objects.equals(evaluationText, purchased.evaluationText) &&
                Objects.equals(evaluationTime, purchased.evaluationTime) &&
                Objects.equals(state, purchased.state);
    }

    @Override
    public int hashCode() {

        return Objects.hash(purchasedId, orderId, time, evaluation, evaluationText, evaluationTime, state);
    }

    public Long getPurchasedId() {

        return purchasedId;
    }

    public void setPurchasedId(Long purchasedId) {
        this.purchasedId = purchasedId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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