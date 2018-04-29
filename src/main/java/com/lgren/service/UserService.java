package com.lgren.service;

import com.lgren.pojo.po.User;

import java.util.List;

public interface UserService {
    Long isByUserIdAndPassword(Long userId, String password);

    String payment(Long userId, String paymentPassword);

    User getUserByUsername(String username);

    long userLogin(String username,String password);

    List<User> selectAll();

    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}