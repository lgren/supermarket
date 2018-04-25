package com.lgren.dao;

import com.lgren.pojo.po.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    User getUserByUsername(String username);

    @Select("select count(user_id) from tb_user where username=#{username} and password = #{password}")
    int userLogin(User user);

    List<User> selectAll();

    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}