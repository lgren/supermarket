package com.lgren.action.admin;

import com.lgren.action.user.dto.UserLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/admin")
public class AdminAction {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpSession session;

    @RequestMapping(value = "adminLogin.do")
    public String toLogin(UserLoginDTO userLoginDTO) {

        return "admin/adminLogin";
    }
    

}
