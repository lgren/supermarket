package com.lgren.pojo.vo;

import com.lgren.pojo.dto.UserDTO;

import java.util.Date;
import java.util.Objects;

public class ShopVO {
    private Long shopId;

    private UserDTO userDTO;

    private String name;

    private String description;

    private Date showTime;

    private Double money;

    private Integer state;

    public ShopVO() {
    }

    public ShopVO(Long shopId, UserDTO userDTO, String name, String description, Date showTime, Double money, Integer state) {

        this.shopId = shopId;
        this.userDTO = userDTO;
        this.name = name;
        this.description = description;
        this.showTime = showTime;
        this.money = money;
        this.state = state;
    }

    @Override
    public String toString() {
        return "ShopVO{" +
                "shopId=" + shopId +
                ", userDTO=" + userDTO +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", showTime=" + showTime +
                ", money=" + money +
                ", state=" + state +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopVO shopVO = (ShopVO) o;
        return Objects.equals(shopId, shopVO.shopId) &&
                Objects.equals(userDTO, shopVO.userDTO) &&
                Objects.equals(name, shopVO.name) &&
                Objects.equals(description, shopVO.description) &&
                Objects.equals(showTime, shopVO.showTime) &&
                Objects.equals(money, shopVO.money) &&
                Objects.equals(state, shopVO.state);
    }

    @Override
    public int hashCode() {

        return Objects.hash(shopId, userDTO, name, description, showTime, money, state);
    }

    public Long getShopId() {

        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getShowTime() {
        return showTime;
    }

    public void setShowTime(Date showTime) {
        this.showTime = showTime;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}