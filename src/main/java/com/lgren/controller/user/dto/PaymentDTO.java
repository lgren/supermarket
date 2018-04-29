package com.lgren.controller.user.dto;

import java.util.List;
import java.util.Objects;

public class PaymentDTO {
    private Double all;

    private String paymentPassword;

    private String authCode;

    private List<Long> orderIdList;

    private List<Long> cartGoodsIdList;

    public PaymentDTO() {
    }

    public PaymentDTO(Double all, String paymentPassword, String authCode, List<Long> orderIdList, List<Long> cartGoodsIdList) {
        this.all = all;
        this.paymentPassword = paymentPassword;
        this.authCode = authCode;
        this.orderIdList = orderIdList;
        this.cartGoodsIdList = cartGoodsIdList;
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "all=" + all +
                ", paymentPassword='" + paymentPassword + '\'' +
                ", authCode='" + authCode + '\'' +
                ", orderIdList=" + orderIdList +
                ", cartGoodsIdList=" + cartGoodsIdList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentDTO that = (PaymentDTO) o;
        return Objects.equals(all, that.all) &&
                Objects.equals(paymentPassword, that.paymentPassword) &&
                Objects.equals(authCode, that.authCode) &&
                Objects.equals(orderIdList, that.orderIdList) &&
                Objects.equals(cartGoodsIdList, that.cartGoodsIdList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(all, paymentPassword, authCode, orderIdList, cartGoodsIdList);
    }

    public Double getAll() {

        return all;
    }

    public void setAll(Double all) {
        this.all = all;
    }

    public String getPaymentPassword() {
        return paymentPassword;
    }

    public void setPaymentPassword(String paymentPassword) {
        this.paymentPassword = paymentPassword;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public List<Long> getOrderIdList() {
        return orderIdList;
    }

    public void setOrderIdList(List<Long> orderIdList) {
        this.orderIdList = orderIdList;
    }

    public List<Long> getCartGoodsIdList() {
        return cartGoodsIdList;
    }

    public void setCartGoodsIdList(List<Long> cartGoodsIdList) {
        this.cartGoodsIdList = cartGoodsIdList;
    }
}
