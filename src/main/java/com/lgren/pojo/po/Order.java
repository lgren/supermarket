package com.lgren.pojo.po;

import java.util.Date;
import java.util.Objects;

public class Order {
    private Long orderId;

    private Long userId;

    private Long shopId;

    private Long goodsId;

    private Integer amount;

    private Double price;

    private Integer payWay;

    private Long orderTime;

    private Integer sendGoods;

    private Date sendGoodsTime;

    private Integer confirm;

    private Date confirmTime;

    private Integer state;

    public Order() {
    }

    public Order(Long orderId, Long userId, Long shopId, Long goodsId, Integer amount, Double price, Integer payWay, Long orderTime, Integer sendGoods, Date sendGoodsTime, Integer confirm, Date confirmTime, Integer state) {

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

    @Override
    public String toString() {
        return "Order{" +
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
                ", confirm=" + confirm +
                ", confirmTime=" + confirmTime +
                ", state=" + state +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId) &&
                Objects.equals(userId, order.userId) &&
                Objects.equals(shopId, order.shopId) &&
                Objects.equals(goodsId, order.goodsId) &&
                Objects.equals(amount, order.amount) &&
                Objects.equals(price, order.price) &&
                Objects.equals(payWay, order.payWay) &&
                Objects.equals(orderTime, order.orderTime) &&
                Objects.equals(sendGoods, order.sendGoods) &&
                Objects.equals(sendGoodsTime, order.sendGoodsTime) &&
                Objects.equals(confirm, order.confirm) &&
                Objects.equals(confirmTime, order.confirmTime) &&
                Objects.equals(state, order.state);
    }

    @Override
    public int hashCode() {

        return Objects.hash(orderId, userId, shopId, goodsId, amount, price, payWay, orderTime, sendGoods, sendGoodsTime, confirm, confirmTime, state);
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

    public Long getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Long orderTime) {
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