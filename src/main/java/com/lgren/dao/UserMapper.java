package com.lgren.dao;

import com.lgren.pojo.po.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
//    @Cacheable(value = "user", key = "'User:'+#user_id")
    @Select("select user_id from tb_user where user_id=#{param1,jdbcType=BIGINT} and password = #{param2,jdbcType=CHAR}")
    Long isByUserIdAndPassword(Long userId, String password);

    @Select("select payment_password from tb_user where user_id=#{param1,jdbcType=BIGINT} and payment_password = #{param2,jdbcType=CHAR}")
    String payment(Long userId, String paymentPassword);

    User getUserByUsername(String username);
//    @Cacheable(key = "#userId",value = "")

    @Select("select user_id from tb_user where username=#{param1,jdbcType=CHAR} and password = #{param2,jdbcType=CHAR}")
    Long userLogin(String username,String password);

    List<User> selectAll();

    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertTest(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}