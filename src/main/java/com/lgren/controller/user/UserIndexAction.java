package com.lgren.controller.user;

import com.lgren.controller.user.dto.MyShopDTO;
import com.lgren.controller.user.dto.UserHtmlDTO;
import com.lgren.controller.user.dto.UserLoginDTO;
import com.lgren.dao.ShopMapper;
import com.lgren.exception.AddException;
import com.lgren.exception.SelectException;
import com.lgren.pojo.dto.CartGoodsDTO;
import com.lgren.pojo.dto.CollectGoodsDTO;
import com.lgren.pojo.po.User;
import com.lgren.service.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

@Controller
public class UserIndexAction {
    @Autowired
    private Mapper mapper;
    @Autowired
    private UserHtmlService userHtmlService;
    @Autowired
    private ShopMapper shopMapper;



    @Autowired
    private UserService userService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private CollectGoodsService collectGoodsService;
    @Autowired
    private CartGoodsService cartGoodsService;
    @Autowired
    private HttpSession session;

    //登陆验证 -1代表账号或密码为空 0为失败 1为成功 2已经登陆了
    @ResponseBody
    @GetMapping(value = "userLogin.do")
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
    //注册

    /**
     *
     * @param userLoginDTO
     * @return // 数字:成功 --- f+* 0:参数问题 1账号或密码不能为空 2账号或密码输入格式不对 10:用户已存在 11:增加用户失败 12:未查找刚插入的用户 13:个人购物车增加失败 14:个人收藏夹添加失败
     *
     */
    @ResponseBody
    @PostMapping(value = "registration.do")
    public String registration(UserLoginDTO userLoginDTO) {
        if (StringUtils.isEmptyOrWhitespace(userLoginDTO.getUsername())
                || StringUtils.isEmptyOrWhitespace(userLoginDTO.getPassword())) {
            return "f1";
        }
        try {
            Long userId = userHtmlService.addUser(userLoginDTO);
            userLogin(userLoginDTO);
            return userId.toString();
        } catch (AddException e) {
            return "f"+e.getMessage();
        }
    }
    //用户基础信息修改 0修改失败 1为修改成功
    @ResponseBody
    @PutMapping(value = "userUpdate.do")
    public int userUpdate(UserHtmlDTO userHtmlDTO) {
        userHtmlDTO.setUserId((Long) session.getAttribute("userId"));
        userHtmlDTO.setUsername(null);
        userHtmlDTO.setPassword(null);
        userHtmlDTO.setPhone(null);
        userHtmlDTO.setEmail(null);
        userHtmlDTO.setMoney(null);
        if (userHtmlDTO.getUserId() == null) {
            return 0;
        }
        User user = mapper.map(userHtmlDTO,User.class);
        user.setGender("男".equals(userHtmlDTO.getGenderStr()) ? 1 : 0);
        return userService.updateByPrimaryKeySelective(user);
    }
    //收藏夹删除商品 0删除失败 1删除成功
    @ResponseBody
    @DeleteMapping(value = "collectGoodsDelete.do/{collectGoodsId}")
    public int collectGoodsDelete(@PathVariable("collectGoodsId") Long collectGoodsId) {
        if (collectGoodsId == null) {
            return 0;
        }
        return collectGoodsService.deleteByPrimaryKey(collectGoodsId);
    }
    //购物车删除商品 0删除失败 1删除成功
    @ResponseBody
    @DeleteMapping(value = "cartGoodsDelete.do/{cartGoodsId}")
    public int cartGoodsDelete(@PathVariable("cartGoodsId") Long cartGoodsId) {
        if (cartGoodsId == null) {
            return 0;
        }
        return cartGoodsService.deleteByPrimaryKey(cartGoodsId);
    }
    //购物车添加商品 -2请先登录 -1该商品失效 0添加失败 1添加成功 2已经添加
    @ResponseBody
    @PostMapping(value = "addCartGoods.do",params = {"goodsId"})
    public int addCartGoods(@RequestParam("goodsId") Long goodsId) {
        CartGoodsDTO cartGoodsDTO = new CartGoodsDTO();
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return -2;
        }else if (goodsService.selectByPrimaryKey(goodsId) == null) {
            return -1;
        } else if (goodsId == null) {
            return 0;
        } else if (cartGoodsService.selectByUserIdandGoodsId(userId,goodsId) != 0){
            return 2;
        }
        return cartGoodsService.insertByUserIdAndGoodsId(userId,goodsId);
    }
    //收藏夹添加商品 -2请先登录 -1该商品失效 0添加失败 1添加成功 2已经添加
    @ResponseBody
    @PostMapping(value = "addCollectGoods.do",params = {"goodsId"})
    public int addCollectGoods(@RequestParam("goodsId") Long goodsId) {
        CollectGoodsDTO collectGoodsDTO = new CollectGoodsDTO();
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return -2;
        }else if (goodsService.selectByPrimaryKey(goodsId) == null) {
            return -1;
        } else if (goodsId == null) {
            return 0;
        } else if (collectGoodsService.selectByUserIdandGoodsId(userId,goodsId) != 0) {
            return 2;
        }
        return collectGoodsService.insertByUserIdAndGoodsId(userId,goodsId);
    }

    //shop基础信息修改 0修改失败 1为修改成功

    /**
     *
     * @param myShopDTO
     * @return //1:修改成功 --- f+ 1:shopId为空 10:未找到shop 11:修改失败
     */
    @ResponseBody
    @PutMapping(value = "userUpdate.do")
    public String shopUpdate(MyShopDTO myShopDTO) {
        if (myShopDTO.getShopId() == null) {
            return "f1";
        }
        try {
            userHtmlService.shopUpdate(myShopDTO);
            return "1";
        } catch (SelectException se) {
            return se.getMessage();
        }
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
