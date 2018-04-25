package com.lgren.pojo.vo;

import com.lgren.pojo.dto.GoodsDTO;
import com.lgren.pojo.dto.ShopDTO;
import com.lgren.pojo.dto.UserDTO;

import java.util.Date;

public class OrderVO {
    private Long orderId;

    private UserDTO userDTO;

    private ShopDTO shopDTO;

    private GoodsDTO goodsDTO;

    private Integer amount;

    private Double price;

    private Integer payWay;

    private Date orderTime;

    private Integer sendGoods;

    private Date sendGoodsTime;

    private Integer confirm;

    private Date confirmTime;

    private Integer state;

}