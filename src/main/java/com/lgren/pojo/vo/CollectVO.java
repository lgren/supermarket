package com.lgren.pojo.vo;

import com.lgren.pojo.dto.UserDTO;

import java.util.List;
import java.util.Objects;

public class CollectVO {
    private Long collectId;

    private UserDTO userDTO;

    private List<CollectGoodsVO> collectGoodsVOList;

    public CollectVO() {
    }

    public CollectVO(Long collectId, UserDTO userDTO, List<CollectGoodsVO> collectGoodsVOList) {

        this.collectId = collectId;
        this.userDTO = userDTO;
        this.collectGoodsVOList = collectGoodsVOList;
    }

    @Override
    public String toString() {
        return "CollectVO{" +
                "collectId=" + collectId +
                ", userDTO=" + userDTO +
                ", collectGoodsVOList=" + collectGoodsVOList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollectVO collectVO = (CollectVO) o;
        return Objects.equals(collectId, collectVO.collectId) &&
                Objects.equals(userDTO, collectVO.userDTO) &&
                Objects.equals(collectGoodsVOList, collectVO.collectGoodsVOList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(collectId, userDTO, collectGoodsVOList);
    }

    public Long getCollectId() {

        return collectId;
    }

    public void setCollectId(Long collectId) {
        this.collectId = collectId;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public List<CollectGoodsVO> getCollectGoodsVOList() {
        return collectGoodsVOList;
    }

    public void setCollectGoodsVOList(List<CollectGoodsVO> collectGoodsVOList) {
        this.collectGoodsVOList = collectGoodsVOList;
    }
}