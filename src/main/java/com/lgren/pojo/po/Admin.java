package com.lgren.pojo.po;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Admin {
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

    private String homeaddress;

    private String liveaddress;

    private Date birthday;

    private BigDecimal wages;

    public Admin() {
    }

    public Admin(Long adminId, Integer position, Long superiorId, String username, String password, String avatarUrl, String nickname, String realName, Integer gender, String phone, String email, String homeaddress, String liveaddress, Date birthday, BigDecimal wages) {

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
        return "Admin{" +
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
        Admin admin = (Admin) o;
        return Objects.equals(adminId, admin.adminId) &&
                Objects.equals(position, admin.position) &&
                Objects.equals(superiorId, admin.superiorId) &&
                Objects.equals(username, admin.username) &&
                Objects.equals(password, admin.password) &&
                Objects.equals(avatarUrl, admin.avatarUrl) &&
                Objects.equals(nickname, admin.nickname) &&
                Objects.equals(realName, admin.realName) &&
                Objects.equals(gender, admin.gender) &&
                Objects.equals(phone, admin.phone) &&
                Objects.equals(email, admin.email) &&
                Objects.equals(homeaddress, admin.homeaddress) &&
                Objects.equals(liveaddress, admin.liveaddress) &&
                Objects.equals(birthday, admin.birthday) &&
                Objects.equals(wages, admin.wages);
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
}