package com.lgren.controller.user.dto;

import java.util.Objects;

public class ApplyShopDTO {
    private Long userId;

    private String name;

    private String description;

    private String authCode;

    public ApplyShopDTO() {
    }

    public ApplyShopDTO(Long userId, String name, String description, String authCode) {

        this.userId = userId;
        this.name = name;
        this.description = description;
        this.authCode = authCode;
    }

    @Override
    public String toString() {
        return "ApplyShopDTO{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", authCode='" + authCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplyShopDTO that = (ApplyShopDTO) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(authCode, that.authCode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, name, description, authCode);
    }

    public Long getUserId() {

        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
