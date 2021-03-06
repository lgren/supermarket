package com.lgren.service;

import com.lgren.pojo.po.ReceivingAddress;

import java.util.List;

public interface ReceivingAddressService {
    List<ReceivingAddress> getReceivingAddressByUserId(Long userId);

    List<ReceivingAddress> selectAll();

    int deleteByPrimaryKey(Long receivingAddressId);

    int insert(ReceivingAddress record);

    int insertSelective(ReceivingAddress record);

    ReceivingAddress selectByPrimaryKey(Long receivingAddressId);

    int updateByPrimaryKeySelective(ReceivingAddress record);

    int updateByPrimaryKey(ReceivingAddress record);
}