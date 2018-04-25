package com.lgren.api.remote;

import com.lgren.pojo.po.User;
import com.lgren.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@RestController
@RequestMapping("/remote")
public class UserRemote {
    @Autowired
    private UserService userService;

    //根据用户名查询
    @RequestMapping(value = "/user.do",params = {"username"})
    public User getUserByUsername(@RequestParam("username") String username) {
        User user = new User();
        if (StringUtils.isEmptyOrWhitespace(username)) {
            return user;
        }
        user = userService.getUserByUsername(username);
        return user;

    }
    //查询所有
    @RequestMapping(value = "/user.do",method = RequestMethod.GET)
    public List<User> getAllUser() {
        return userService.selectAll();
    }
    //根据主键ID查询
    public User getUserById(Long userId) {
        return userService.selectByPrimaryKey(userId);
    }
    //添加
    @RequestMapping(value = "/user.do",method = RequestMethod.POST)
    public boolean addUser(User user) {
        return userService.insertSelective(user) > 0 ? true : false;
    }
    //通过主键ID删除
    @RequestMapping(value = "/user.do/{userId}",method = RequestMethod.DELETE)
    public boolean deleteUserById(@PathVariable("userId") Long userId) {
        return userService.deleteByPrimaryKey(userId) > 0 ? true : false;
    }
    //通过传输一个User对象修改
    @RequestMapping(value = "/user.do",method = RequestMethod.PUT)
    public boolean updateUserById(User user) {
        return userService.updateByPrimaryKeySelective(user) > 0 ? true : false;
    }



}
