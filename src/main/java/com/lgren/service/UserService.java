package com.lgren.service;

import com.lgren.pojo.po.User;

import java.util.List;

public interface UserService {
    User getUserByUsername(String username);

    boolean userLogin(User user);

    List<User> selectAll();

    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}