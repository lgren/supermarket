package com.lgren.pojo.po;

import java.util.Objects;

public class ReceivingAddress {
    private Long receivingAddressId;

    private Long userId;

    private String receivingName;

    private String area;

    private String address;

    private String postCode;

    private String phone;

    public ReceivingAddress() {
    }

    public ReceivingAddress(Long receivingAddressId, Long userId, String receivingName, String area, String address, String postCode, String phone) {

        this.receivingAddressId = receivingAddressId;
        this.userId = userId;
        this.receivingName = receivingName;
        this.area = area;
        this.address = address;
        this.postCode = postCode;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "ReceivingAddress{" +
                "receivingAddressId=" + receivingAddressId +
                ", userId=" + userId +
                ", receivingName='" + receivingName + '\'' +
                ", area='" + area + '\'' +
                ", address='" + address + '\'' +
                ", postCode='" + postCode + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceivingAddress that = (ReceivingAddress) o;
        return Objects.equals(receivingAddressId, that.receivingAddressId) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(receivingName, that.receivingName) &&
                Objects.equals(area, that.area) &&
                Objects.equals(address, that.address) &&
                Objects.equals(postCode, that.postCode) &&
                Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {

        return Objects.hash(receivingAddressId, userId, receivingName, area, address, postCode, phone);
    }

    public Long getReceivingAddressId() {

        return receivingAddressId;
    }

    public void setReceivingAddressId(Long receivingAddressId) {
        this.receivingAddressId = receivingAddressId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getReceivingName() {
        return receivingName;
    }

    public void setReceivingName(String receivingName) {
        this.receivingName = receivingName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}