package com.lgren.pojo.dto;

import java.util.Date;
import java.util.Objects;

public class RefundDTO {
    private Long refundId;

    private Long orderId;

    private Long purchasedId;

    private Integer type;

    private String reason;

    private Date applyTime;

    private Integer state;

    private Date solveTime;

    public RefundDTO() {
    }

    public RefundDTO(Long refundId, Long orderId, Long purchasedId, Integer type, String reason, Date applyTime, Integer state, Date solveTime) {

        this.refundId = refundId;
        this.orderId = orderId;
        this.purchasedId = purchasedId;
        this.type = type;
        this.reason = reason;
        this.applyTime = applyTime;
        this.state = state;
        this.solveTime = solveTime;
    }

    @Override
    public String toString() {
        return "RefundDTO{" +
                "refundId=" + refundId +
                ", orderId=" + orderId +
                ", purchasedId=" + purchasedId +
                ", type=" + type +
                ", reason='" + reason + '\'' +
                ", applyTime=" + applyTime +
                ", state=" + state +
                ", solveTime=" + solveTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RefundDTO refund = (RefundDTO) o;
        return Objects.equals(refundId, refund.refundId) &&
                Objects.equals(orderId, refund.orderId) &&
                Objects.equals(purchasedId, refund.purchasedId) &&
                Objects.equals(type, refund.type) &&
                Objects.equals(reason, refund.reason) &&
                Objects.equals(applyTime, refund.applyTime) &&
                Objects.equals(state, refund.state) &&
                Objects.equals(solveTime, refund.solveTime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(refundId, orderId, purchasedId, type, reason, applyTime, state, solveTime);
    }

    public Long getRefundId() {

        return refundId;
    }

    public void setRefundId(Long refundId) {
        this.refundId = refundId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getPurchasedId() {
        return purchasedId;
    }

    public void setPurchasedId(Long purchasedId) {
        this.purchasedId = purchasedId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getSolveTime() {
        return solveTime;
    }

    public void setSolveTime(Date solveTime) {
        this.solveTime = solveTime;
    }
}