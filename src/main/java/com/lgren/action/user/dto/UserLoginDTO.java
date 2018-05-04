package com.lgren.action.user.dto;

import java.util.Objects;

public class UserLoginDTO {
    private String userId;

    private String username;

    private String password;

    private String paymentPassword;

    private String authCode;

    private boolean autoLogin;

    public UserLoginDTO() {
    }

    public UserLoginDTO(String userId, String username, String password, String paymentPassword, String authCode, boolean autoLogin) {

        this.userId = userId;
        this.username = username;
        this.password = password;
        this.paymentPassword = paymentPassword;
        this.authCode = authCode;
        this.autoLogin = autoLogin;
    }

    @Override
    public String toString() {
        return "UserLoginDTO{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", paymentPassword='" + paymentPassword + '\'' +
                ", authCode='" + authCode + '\'' +
                ", autoLogin=" + autoLogin +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLoginDTO that = (UserLoginDTO) o;
        return autoLogin == that.autoLogin &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(paymentPassword, that.paymentPassword) &&
                Objects.equals(authCode, that.authCode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, username, password, paymentPassword, authCode, autoLogin);
    }

    public String getUserId() {

        return userId;
    }

    public void setUserId(String userId) {
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

    public String getPaymentPassword() {
        return paymentPassword;
    }

    public void setPaymentPassword(String paymentPassword) {
        this.paymentPassword = paymentPassword;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public boolean isAutoLogin() {
        return autoLogin;
    }

    public void setAutoLogin(boolean autoLogin) {
        this.autoLogin = autoLogin;
    }
}
