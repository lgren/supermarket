package com.lgren.controller.user;

import com.lgren.controller.user.dto.UserLoginDTO;
import com.lgren.pojo.po.User;
import com.lgren.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

@Controller
public class UserIndexAction {
    @Autowired
    private Mapper mapper;
    @Autowired
    private UserService userService;
    @Autowired
    private HttpSession session;

    //登陆验证 -1代表账号或密码为空 0为失败 1为成功 2已经登陆了
    @ResponseBody
    @RequestMapping(value = "userLogin.do",method = RequestMethod.GET)
    public Long userLogin(UserLoginDTO userLoginDTO) {
        if (StringUtils.isEmptyOrWhitespace(userLoginDTO.getUsername())
                || StringUtils.isEmptyOrWhitespace(userLoginDTO.getPassword())) {
            return -1L;
        }
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(userLoginDTO.getUsername(),userLoginDTO.getPassword());
            try {
                currentUser.login(token);
                session.setAttribute("username",userLoginDTO.getUsername());
                Long userId = userService.getUserByUsername(userLoginDTO.getUsername()).getUserId();
                session.setAttribute("userId",userId);
                return userId;
            } catch (AuthenticationException ae) {
                return 0L;
            }
        }
        return 2L;
    }
    //注册验证 -1代表账号或密码为空 0为代表账号已经存在 1为成功
    @ResponseBody
    @RequestMapping(value = "registration.do",method = RequestMethod.POST)
    public int registration(UserLoginDTO userLoginDTO) {
        if (StringUtils.isEmptyOrWhitespace(userLoginDTO.getUsername())
                || StringUtils.isEmptyOrWhitespace(userLoginDTO.getPassword())) {
            return -1;
        }
        User user = userService.getUserByUsername(userLoginDTO.getUsername());
        if (user == null) {
            userService.insertSelective(mapper.map(userLoginDTO,User.class));
            userLogin(userLoginDTO);
            return 1;
        }
        return 0;
    }














    //测试shiro
    @ResponseBody
    @RequestMapping(value = "/shiroTest.do", method = RequestMethod.GET)
    public String shiroTest() {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        System.out.println(session.getAttribute("username"));
        System.out.println((this.session).getAttribute("username"));
        return "test";
    }

/*    @ResponseBody
    @RequestMapping("/login.do")
    public boolean login(UserLogin userLogin) {
        System.out.println(userLogin);
        return userService.userLogin(userLogin);
    }*/
    @ResponseBody
    @RequestMapping("/test3333")
    public String test1() {
        return "test433443";
    }
//id name age Class

//    {"id":12,"name":"nihao"}

//    {"id":12,"name":"nihao","age":23,"class":{{}}}
//    @ResponseBody
    @RequestMapping(value = "/test333",method = RequestMethod.GET)
    public String user() {
        return "index";
    }

/*    @RequestMapping(value = "/index")
    public String index1(Map<String,Object> map){
        List<Test> list = testService.getTestList();
        map.put("tests",list);
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/")
    public List<Test> index(){
        return testService.getTestList();
    }*/


}
