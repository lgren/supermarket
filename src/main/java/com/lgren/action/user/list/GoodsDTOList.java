package com.lgren.action.user.list;

import com.lgren.pojo.dto.GoodsDTO;

import java.util.List;
import java.util.Objects;

public class GoodsDTOList {
    private List<GoodsDTO> list;

    public GoodsDTOList() {
    }

    public GoodsDTOList(List<GoodsDTO> list) {

        this.list = list;
    }

    @Override
    public String toString() {
        return "GoodsDTOList{" +
                "list=" + list +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodsDTOList that = (GoodsDTOList) o;
        return Objects.equals(list, that.list);
    }

    @Override
    public int hashCode() {

        return Objects.hash(list);
    }

    public List<GoodsDTO> getList() {

        return list;
    }

    public void setList(List<GoodsDTO> list) {
        this.list = list;
    }
}
