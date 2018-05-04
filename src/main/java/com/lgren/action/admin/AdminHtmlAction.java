package com.lgren.action.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
public class AdminHtmlAction {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpSession session;

    @RequestMapping(value = {"/toLogin", "/",""})
    public String toLogin(Map map) {
        map.put("requestURL", request.getHeader("Referer"));
        return "admin/adminLogin";
    }


}
