package com.lgren.pojo.vo;

import com.lgren.pojo.dto.OrderDTO;
import com.lgren.pojo.dto.PurchasedDTO;

import java.util.Date;

public class RefundVO {
    private Long refundId;

    private OrderDTO orderDTO;

    private PurchasedDTO purchasedDTO;

    private Integer type;

    private String reason;

    private Date applyTime;

    private Integer state;

    private Date solveTime;

}