package com.lgren.pojo.dto;

import java.util.Date;
import java.util.Objects;

public class OrderDTO {
    private Long orderId;

    private Long userId;

    private Long shopId;

    private Long goodsId;

    private Integer amount;

    private Double price;

    private Integer payWay;

    private Date orderTime;

    private Integer sendGoods;

    private Date sendGoodsTime;

    private Long sendGoodsId;

    private Integer sendGoodsState;

    private Integer confirm;

    private Date confirmTime;

    private Integer state;

    public OrderDTO() {
    }

    public OrderDTO(Long orderId, Long userId, Long shopId, Long goodsId, Integer amount, Double price, Integer payWay, Date orderTime, Integer sendGoods, Date sendGoodsTime, Integer confirm, Date confirmTime, Integer state) {
        this.orderId = orderId;
        this.userId = userId;
        this.shopId = shopId;
        this.goodsId = goodsId;
        this.amount = amount;
        this.price = price;
        this.payWay = payWay;
        this.orderTime = orderTime;
        this.sendGoods = sendGoods;
        this.sendGoodsTime = sendGoodsTime;
        this.confirm = confirm;
        this.confirmTime = confirmTime;
        this.state = state;
    }

    public OrderDTO(Long orderId, Long userId, Long shopId, Long goodsId, Integer amount, Double price, Integer payWay, Date orderTime, Integer sendGoods, Date sendGoodsTime, Long sendGoodsId, Integer sendGoodsState, Integer confirm, Date confirmTime, Integer state) {

        this.orderId = orderId;
        this.userId = userId;
        this.shopId = shopId;
        this.goodsId = goodsId;
        this.amount = amount;
        this.price = price;
        this.payWay = payWay;
        this.orderTime = orderTime;
        this.sendGoods = sendGoods;
        this.sendGoodsTime = sendGoodsTime;
        this.sendGoodsId = sendGoodsId;
        this.sendGoodsState = sendGoodsState;
        this.confirm = confirm;
        this.confirmTime = confirmTime;
        this.state = state;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", shopId=" + shopId +
                ", goodsId=" + goodsId +
                ", amount=" + amount +
                ", price=" + price +
                ", payWay=" + payWay +
                ", orderTime=" + orderTime +
                ", sendGoods=" + sendGoods +
                ", sendGoodsTime=" + sendGoodsTime +
                ", sendGoodsId=" + sendGoodsId +
                ", sendGoodsState=" + sendGoodsState +
                ", confirm=" + confirm +
                ", confirmTime=" + confirmTime +
                ", state=" + state +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDTO orderDTO = (OrderDTO) o;
        return Objects.equals(orderId, orderDTO.orderId) &&
                Objects.equals(userId, orderDTO.userId) &&
                Objects.equals(shopId, orderDTO.shopId) &&
                Objects.equals(goodsId, orderDTO.goodsId) &&
                Objects.equals(amount, orderDTO.amount) &&
                Objects.equals(price, orderDTO.price) &&
                Objects.equals(payWay, orderDTO.payWay) &&
                Objects.equals(orderTime, orderDTO.orderTime) &&
                Objects.equals(sendGoods, orderDTO.sendGoods) &&
                Objects.equals(sendGoodsTime, orderDTO.sendGoodsTime) &&
                Objects.equals(sendGoodsId, orderDTO.sendGoodsId) &&
                Objects.equals(sendGoodsState, orderDTO.sendGoodsState) &&
                Objects.equals(confirm, orderDTO.confirm) &&
                Objects.equals(confirmTime, orderDTO.confirmTime) &&
                Objects.equals(state, orderDTO.state);
    }

    @Override
    public int hashCode() {

        return Objects.hash(orderId, userId, shopId, goodsId, amount, price, payWay, orderTime, sendGoods, sendGoodsTime, sendGoodsId, sendGoodsState, confirm, confirmTime, state);
    }

    public Long getOrderId() {

        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getPayWay() {
        return payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getSendGoods() {
        return sendGoods;
    }

    public void setSendGoods(Integer sendGoods) {
        this.sendGoods = sendGoods;
    }

    public Date getSendGoodsTime() {
        return sendGoodsTime;
    }

    public void setSendGoodsTime(Date sendGoodsTime) {
        this.sendGoodsTime = sendGoodsTime;
    }

    public Long getSendGoodsId() {
        return sendGoodsId;
    }

    public void setSendGoodsId(Long sendGoodsId) {
        this.sendGoodsId = sendGoodsId;
    }

    public Integer getSendGoodsState() {
        return sendGoodsState;
    }

    public void setSendGoodsState(Integer sendGoodsState) {
        this.sendGoodsState = sendGoodsState;
    }

    public Integer getConfirm() {
        return confirm;
    }

    public void setConfirm(Integer confirm) {
        this.confirm = confirm;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}