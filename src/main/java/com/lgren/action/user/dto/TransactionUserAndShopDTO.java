package com.lgren.action.user.dto;

import java.util.*;

public class TransactionUserAndShopDTO {
    private Double all;

    private UserLoginDTO userPaymentDTO;

    private List<ShopOrderGetDTO> shopOrderGetDTOList;

    public TransactionUserAndShopDTO() {
    }

    public TransactionUserAndShopDTO(Double all, UserLoginDTO userPaymentDTO, List<ShopOrderGetDTO> shopOrderGetDTOList) {

        this.all = all;
        this.userPaymentDTO = userPaymentDTO;
        this.shopOrderGetDTOList = shopOrderGetDTOList;
    }

    @Override
    public String toString() {
        return "TransactionUserAndShopDTO{" +
                "all=" + all +
                ", userPaymentDTO=" + userPaymentDTO +
                ", shopOrderGetDTOList=" + shopOrderGetDTOList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionUserAndShopDTO that = (TransactionUserAndShopDTO) o;
        return Objects.equals(all, that.all) &&
                Objects.equals(userPaymentDTO, that.userPaymentDTO) &&
                Objects.equals(shopOrderGetDTOList, that.shopOrderGetDTOList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(all, userPaymentDTO, shopOrderGetDTOList);
    }

    public Double getAll() {

        return all;
    }

    public void setAll(Double all) {
        this.all = all;
    }

    public UserLoginDTO getUserPaymentDTO() {
        return userPaymentDTO;
    }

    public void setUserPaymentDTO(UserLoginDTO userPaymentDTO) {
        this.userPaymentDTO = userPaymentDTO;
    }

    public List<ShopOrderGetDTO> getShopOrderGetDTOList() {
        return shopOrderGetDTOList;
    }

    public void setShopOrderGetDTOList(List<ShopOrderGetDTO> shopOrderGetDTOList) {
        this.shopOrderGetDTOList = shopOrderGetDTOList;
    }
}
