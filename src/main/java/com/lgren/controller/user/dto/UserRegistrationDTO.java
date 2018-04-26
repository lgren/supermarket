package com.lgren.controller.user.dto;

import java.util.Objects;

public class UserRegistrationDTO {
    private String userId;

    private String nickname;

    private String username;

    private String password;

    private String authCode;

    private String autoLogin;

    public UserRegistrationDTO() {
    }

    public UserRegistrationDTO(String userId, String nickname, String username, String password, String authCode, String autoLogin) {

        this.userId = userId;
        this.nickname = nickname;
        this.username = username;
        this.password = password;
        this.authCode = authCode;
        this.autoLogin = autoLogin;
    }

    @Override
    public String toString() {
        return "UserRegistrationDTO{" +
                "userId='" + userId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authCode='" + authCode + '\'' +
                ", autoLogin='" + autoLogin + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRegistrationDTO that = (UserRegistrationDTO) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(nickname, that.nickname) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(authCode, that.authCode) &&
                Objects.equals(autoLogin, that.autoLogin);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, nickname, username, password, authCode, autoLogin);
    }

    public String getUserId() {

        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getAutoLogin() {
        return autoLogin;
    }

    public void setAutoLogin(String autoLogin) {
        this.autoLogin = autoLogin;
    }
}
