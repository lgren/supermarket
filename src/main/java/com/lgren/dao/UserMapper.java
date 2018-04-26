package com.lgren.dao;

import com.lgren.pojo.po.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    User getUserByUsername(String username);

//    @Cacheable(key = "#userId",value = "")
    @Select("select user_id from tb_user where username=#{param1,jdbcType=CHAR} and password = #{param2,jdbcType=CHAR}")
    long userLogin(String username,String password);

    List<User> selectAll();

    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}