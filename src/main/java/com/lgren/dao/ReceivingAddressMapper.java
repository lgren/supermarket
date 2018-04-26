package com.lgren.dao;

import com.lgren.pojo.po.ReceivingAddress;

import java.util.List;

public interface ReceivingAddressMapper {
    List<ReceivingAddress> getReceivingAddressByUserId(Long userId);

    List<ReceivingAddress> selectAll();

    int deleteByPrimaryKey(Long receivingAddressId);

    int insert(ReceivingAddress record);

    int insertSelective(ReceivingAddress record);

    ReceivingAddress selectByPrimaryKey(Long receivingAddressId);

    int updateByPrimaryKeySelective(ReceivingAddress record);

    int updateByPrimaryKey(ReceivingAddress record);
}