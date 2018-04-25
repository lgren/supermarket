package com.lgren.api.moudle;

import com.lgren.pojo.dto.UserDTO;
import com.lgren.pojo.po.User;
import com.lgren.service.UserService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserApi {//OK
    @Autowired
    private Mapper mapper;
    @Autowired
    private UserService userService;
    //根据用户名查询
    public UserDTO getUserByUsername(String username) {
        if (StringUtils.isEmptyOrWhitespace(username)) {
            return new UserDTO();
        }
        return mapper.map(userService.getUserByUsername(username),UserDTO.class);
    }
    //查询所有
    public List<UserDTO> getAllUser() {
        return userService.selectAll().stream()
                .map(u -> mapper.map(u,UserDTO.class))
                .collect(Collectors.toList());
    }
    //根据主键ID查询
    public UserDTO getUserById(Long userId) {
        return mapper.map(userService.selectByPrimaryKey(userId),UserDTO.class);
    }
    //添加
    public boolean addUser(UserDTO userDTO) {
        return userService.insertSelective(mapper.map(userDTO,User.class)) > 0 ? true : false;
    }
    //通过主键ID删除
    public boolean deleteUserById(Long userId) {
        return userService.deleteByPrimaryKey(userId) > 0 ? true : false;
    }
    //通过传输一个User对象修改
    public boolean updateUserById(UserDTO userDTO) {
        return userService.updateByPrimaryKeySelective(mapper.map(userDTO,User.class)) > 0 ? true : false;
    }
}
