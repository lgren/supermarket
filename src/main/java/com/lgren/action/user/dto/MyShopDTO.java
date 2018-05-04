package com.lgren.action.user.dto;

import java.util.Objects;

public class MyShopDTO {
    private Long shopId;

    private String name;

    private Integer state;

    private String description;

    private String authCode;

    public MyShopDTO() {
    }

    public MyShopDTO(Long shopId, String name, Integer state, String description, String authCode) {

        this.shopId = shopId;
        this.name = name;
        this.state = state;
        this.description = description;
        this.authCode = authCode;
    }

    @Override
    public String toString() {
        return "MyShopDTO{" +
                "shopId=" + shopId +
                ", name='" + name + '\'' +
                ", state=" + state +
                ", description='" + description + '\'' +
                ", authCode='" + authCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyShopDTO myShopDTO = (MyShopDTO) o;
        return Objects.equals(shopId, myShopDTO.shopId) &&
                Objects.equals(name, myShopDTO.name) &&
                Objects.equals(state, myShopDTO.state) &&
                Objects.equals(description, myShopDTO.description) &&
                Objects.equals(authCode, myShopDTO.authCode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(shopId, name, state, description, authCode);
    }

    public Long getShopId() {

        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }
}
