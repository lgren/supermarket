package com.lgren.pojo.po;

import java.util.Date;
import java.util.Objects;

public class User {
    private Long userId;

    private String username;

    private String password;

    private String avatarUrl;

    private String nickname;

    private String realName;

    private Integer gender;

    private String phone;

    private Date birthday;

    private String email;

    private String homeaddress;

    private String liveaddress;

    private Double money;

    private String paymentPassword;

    public User() {
    }

    public User(Long userId, String username, String password, String avatarUrl, String nickname, String realName, Integer gender, String phone, Date birthday, String email, String homeaddress, String liveaddress, Double money, String paymentPassword) {

        this.userId = userId;
        this.username = username;
        this.password = password;
        this.avatarUrl = avatarUrl;
        this.nickname = nickname;
        this.realName = realName;
        this.gender = gender;
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
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", nickname='" + nickname + '\'' +
                ", realName='" + realName + '\'' +
                ", gender=" + gender +
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
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(avatarUrl, user.avatarUrl) &&
                Objects.equals(nickname, user.nickname) &&
                Objects.equals(realName, user.realName) &&
                Objects.equals(gender, user.gender) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(birthday, user.birthday) &&
                Objects.equals(email, user.email) &&
                Objects.equals(homeaddress, user.homeaddress) &&
                Objects.equals(liveaddress, user.liveaddress) &&
                Objects.equals(money, user.money) &&
                Objects.equals(paymentPassword, user.paymentPassword);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, username, password, avatarUrl, nickname, realName, gender, phone, birthday, email, homeaddress, liveaddress, money, paymentPassword);
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