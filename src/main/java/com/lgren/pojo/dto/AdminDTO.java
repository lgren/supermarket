package com.lgren.pojo.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class AdminDTO {
    private Long adminId;

    private Integer position;

    private Long superiorId;

    private String username;

    private String password;

    private String avatarUrl;

    private String nickname;

    private String realName;

    private Integer gender;

    private String phone;

    private String email;

    public AdminDTO() {
    }

    public AdminDTO(Long adminId, Integer position, Long superiorId, String username, String password, String avatarUrl, String nickname, String realName, Integer gender, String phone, String email, String homeaddress, String liveaddress, Date birthday, BigDecimal wages) {

        this.adminId = adminId;
        this.position = position;
        this.superiorId = superiorId;
        this.username = username;
        this.password = password;
        this.avatarUrl = avatarUrl;
        this.nickname = nickname;
        this.realName = realName;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.homeaddress = homeaddress;
        this.liveaddress = liveaddress;
        this.birthday = birthday;
        this.wages = wages;
    }

    @Override
    public String toString() {
        return "AdminDTO{" +
                "adminId=" + adminId +
                ", position=" + position +
                ", superiorId=" + superiorId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", nickname='" + nickname + '\'' +
                ", realName='" + realName + '\'' +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", homeaddress='" + homeaddress + '\'' +
                ", liveaddress='" + liveaddress + '\'' +
                ", birthday=" + birthday +
                ", wages=" + wages +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminDTO adminDTO = (AdminDTO) o;
        return Objects.equals(adminId, adminDTO.adminId) &&
                Objects.equals(position, adminDTO.position) &&
                Objects.equals(superiorId, adminDTO.superiorId) &&
                Objects.equals(username, adminDTO.username) &&
                Objects.equals(password, adminDTO.password) &&
                Objects.equals(avatarUrl, adminDTO.avatarUrl) &&
                Objects.equals(nickname, adminDTO.nickname) &&
                Objects.equals(realName, adminDTO.realName) &&
                Objects.equals(gender, adminDTO.gender) &&
                Objects.equals(phone, adminDTO.phone) &&
                Objects.equals(email, adminDTO.email) &&
                Objects.equals(homeaddress, adminDTO.homeaddress) &&
                Objects.equals(liveaddress, adminDTO.liveaddress) &&
                Objects.equals(birthday, adminDTO.birthday) &&
                Objects.equals(wages, adminDTO.wages);
    }

    @Override
    public int hashCode() {

        return Objects.hash(adminId, position, superiorId, username, password, avatarUrl, nickname, realName, gender, phone, email, homeaddress, liveaddress, birthday, wages);
    }

    public Long getAdminId() {

        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Long getSuperiorId() {
        return superiorId;
    }

    public void setSuperiorId(Long superiorId) {
        this.superiorId = superiorId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomeaddress() {
        return homeaddress;
    }

    public void setHomeaddress(String homeaddress) {
        this.homeaddress = homeaddress;
    }

    public String getLiveaddress() {
        return liveaddress;
    }

    public void setLiveaddress(String liveaddress) {
        this.liveaddress = liveaddress;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public BigDecimal getWages() {
        return wages;
    }

    public void setWages(BigDecimal wages) {
        this.wages = wages;
    }

    private String homeaddress;

    private String liveaddress;

    private Date birthday;

    private BigDecimal wages;

}