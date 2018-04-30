package com.lgren.pojo.vo;

import com.lgren.pojo.dto.GoodsDTO;
import com.lgren.pojo.dto.ShopDTO;
import com.lgren.pojo.dto.UserDTO;

import java.util.Date;
import java.util.Objects;

public class OrderVO {
    private Long orderId;

    private UserDTO userDTO;

    private ShopDTO shopDTO;

    private GoodsDTO goodsDTO;

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

    public OrderVO() {
    }

    public OrderVO(Long orderId, UserDTO userDTO, ShopDTO shopDTO, GoodsDTO goodsDTO, Integer amount, Double price, Integer payWay, Date orderTime, Integer sendGoods, Date sendGoodsTime, Long sendGoodsId, Integer sendGoodsState, Integer confirm, Date confirmTime, Integer state) {

        this.orderId = orderId;
        this.userDTO = userDTO;
        this.shopDTO = shopDTO;
        this.goodsDTO = goodsDTO;
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
        return "OrderVO{" +
                "orderId=" + orderId +
                ", userDTO=" + userDTO +
                ", shopDTO=" + shopDTO +
                ", goodsDTO=" + goodsDTO +
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
        OrderVO orderVO = (OrderVO) o;
        return Objects.equals(orderId, orderVO.orderId) &&
                Objects.equals(userDTO, orderVO.userDTO) &&
                Objects.equals(shopDTO, orderVO.shopDTO) &&
                Objects.equals(goodsDTO, orderVO.goodsDTO) &&
                Objects.equals(amount, orderVO.amount) &&
                Objects.equals(price, orderVO.price) &&
                Objects.equals(payWay, orderVO.payWay) &&
                Objects.equals(orderTime, orderVO.orderTime) &&
                Objects.equals(sendGoods, orderVO.sendGoods) &&
                Objects.equals(sendGoodsTime, orderVO.sendGoodsTime) &&
                Objects.equals(sendGoodsId, orderVO.sendGoodsId) &&
                Objects.equals(sendGoodsState, orderVO.sendGoodsState) &&
                Objects.equals(confirm, orderVO.confirm) &&
                Objects.equals(confirmTime, orderVO.confirmTime) &&
                Objects.equals(state, orderVO.state);
    }

    @Override
    public int hashCode() {

        return Objects.hash(orderId, userDTO, shopDTO, goodsDTO, amount, price, payWay, orderTime, sendGoods, sendGoodsTime, sendGoodsId, sendGoodsState, confirm, confirmTime, state);
    }

    public Long getOrderId() {

        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public ShopDTO getShopDTO() {
        return shopDTO;
    }

    public void setShopDTO(ShopDTO shopDTO) {
        this.shopDTO = shopDTO;
    }

    public GoodsDTO getGoodsDTO() {
        return goodsDTO;
    }

    public void setGoodsDTO(GoodsDTO goodsDTO) {
        this.goodsDTO = goodsDTO;
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