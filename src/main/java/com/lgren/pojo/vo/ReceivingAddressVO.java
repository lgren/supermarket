package com.lgren.pojo.vo;

import com.lgren.pojo.dto.UserDTO;

import java.util.Objects;

public class ReceivingAddressVO {
    private Long receivingAddressId;

    private UserDTO userDTO;

    private String receivingName;

    private String area;

    private String address;

    private String postCode;

    private String phone;

    public ReceivingAddressVO() {
    }

    public ReceivingAddressVO(Long receivingAddressId, UserDTO userDTO, String receivingName, String area, String address, String postCode, String phone) {

        this.receivingAddressId = receivingAddressId;
        this.userDTO = userDTO;
        this.receivingName = receivingName;
        this.area = area;
        this.address = address;
        this.postCode = postCode;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "ReceivingAddressVO{" +
                "receivingAddressId=" + receivingAddressId +
                ", userDTO=" + userDTO +
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
        ReceivingAddressVO that = (ReceivingAddressVO) o;
        return Objects.equals(receivingAddressId, that.receivingAddressId) &&
                Objects.equals(userDTO, that.userDTO) &&
                Objects.equals(receivingName, that.receivingName) &&
                Objects.equals(area, that.area) &&
                Objects.equals(address, that.address) &&
                Objects.equals(postCode, that.postCode) &&
                Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {

        return Objects.hash(receivingAddressId, userDTO, receivingName, area, address, postCode, phone);
    }

    public Long getReceivingAddressId() {

        return receivingAddressId;
    }

    public void setReceivingAddressId(Long receivingAddressId) {
        this.receivingAddressId = receivingAddressId;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
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
