package com.lgren.controller.user.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

public class UserHtmlDTO {
    private Long userId;

    private String username;

    private String password;

    private String avatarUrl;

    private String nickname;

    private String realName;

    private String genderStr;

    private String phone;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private String email;

    private String homeaddress;

    private String liveaddress;

    private Double money;

    private String paymentPassword;

    public UserHtmlDTO() {
    }

    public UserHtmlDTO(Long userId, String username, String password, String avatarUrl, String nickname, String realName, String genderStr, String phone, Date birthday, String email, String homeaddress, String liveaddress, Double money, String paymentPassword) {

        this.userId = userId;
        this.username = username;
        this.password = password;
        this.avatarUrl = avatarUrl;
        this.nickname = nickname;
        this.realName = realName;
        this.genderStr = genderStr;
        this.phone = phone;
        this.birthday = birthday;
        this.email = email;
        this.homeaddress = homeaddress;
        this.liveaddress = liveaddress;
        this.money = money;
        this.paymentPassword = paymentPassword;
    }

    @Override
    public String toString() {
        return "UserHtmlDTO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", nickname='" + nickname + '\'' +
                ", realName='" + realName + '\'' +
                ", genderStr='" + genderStr + '\'' +
                ", phone='" + phone + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", homeaddress='" + homeaddress + '\'' +
                ", liveaddress='" + liveaddress + '\'' +
                ", money=" + money +
                ", paymentPassword='" + paymentPassword + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserHtmlDTO that = (UserHtmlDTO) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(avatarUrl, that.avatarUrl) &&
                Objects.equals(nickname, that.nickname) &&
                Objects.equals(realName, that.realName) &&
                Objects.equals(genderStr, that.genderStr) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(birthday, that.birthday) &&
                Objects.equals(email, that.email) &&
                Objects.equals(homeaddress, that.homeaddress) &&
                Objects.equals(liveaddress, that.liveaddress) &&
                Objects.equals(money, that.money) &&
                Objects.equals(paymentPassword, that.paymentPassword);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, username, password, avatarUrl, nickname, realName, genderStr, phone, birthday, email, homeaddress, liveaddress, money, paymentPassword);
    }

    public Long getUserId() {

        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getGenderStr() {
        return genderStr;
    }

    public void setGenderStr(String genderStr) {
        this.genderStr = genderStr;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getPaymentPassword() {
        return paymentPassword;
    }

    public void setPaymentPassword(String paymentPassword) {
        this.paymentPassword = paymentPassword;
    }
}
