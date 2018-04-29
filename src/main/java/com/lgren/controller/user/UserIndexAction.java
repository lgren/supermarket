package com.lgren.controller.user;

import com.lgren.controller.user.dto.*;
import com.lgren.dao.ShopMapper;
import com.lgren.exception.TransactionException;
import com.lgren.pojo.dto.CartGoodsDTO;
import com.lgren.pojo.dto.CollectGoodsDTO;
import com.lgren.pojo.dto.GoodsDTO;
import com.lgren.pojo.dto.ReceivingAddressDTO;
import com.lgren.pojo.po.User;
import com.lgren.pojo.vo.CartVO;
import com.lgren.service.*;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

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

    /**
     * @param userLoginDTO
     * @return //数字(userId)代表成功 ---f+ 0:用户名或密码错误 1用户名或密码为空 2已经登陆了
     */
    @ResponseBody
    @GetMapping(value = "userLogin.do")
    public String userLogin(UserLoginDTO userLoginDTO) {
        if (StringUtils.isEmptyOrWhitespace(userLoginDTO.getUsername())
                || StringUtils.isEmptyOrWhitespace(userLoginDTO.getPassword())) {
            return "f1";
        }
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(userLoginDTO.getUsername(), userLoginDTO.getPassword());
            try {
                currentUser.login(token);
                session.setAttribute("username", userLoginDTO.getUsername());
                Long userId = userService.getUserByUsername(userLoginDTO.getUsername()).getUserId();
                session.setAttribute("userId", userId);
                return userId.toString();
            } catch (AuthenticationException ae) {
                return "f0";
            }
        }
        return "f2";
    }
    //注册

    /**
     * @param userRegistrationDTO
     * @return // 数字:成功 --- f+* 0:参数问题 1账号或密码不能为空 2账号或密码输入格式不对 10:用户已存在 11:增加用户失败 12:未查找刚插入的用户 13:个人购物车增加失败 14:个人收藏夹添加失败
     */
    @ResponseBody
    @PostMapping(value = "registration.do")
    public String registration(UserRegistrationDTO userRegistrationDTO) {
        if (StringUtils.isEmptyOrWhitespace(userRegistrationDTO.getUsername())
                || StringUtils.isEmptyOrWhitespace(userRegistrationDTO.getPassword())) {
            return "f1";
        }
        try {
            Long userId = userHtmlService.addUser(userRegistrationDTO);
            userLogin(mapper.map(userRegistrationDTO, UserLoginDTO.class));
            return userId.toString();
        } catch (RuntimeException e) {
            return "f" + e.getMessage();
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
        User user = mapper.map(userHtmlDTO, User.class);
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

    /**
     *
     * @param cartGoodsDTO
     * @return //1:代表成功 0:代表失败 --- f+ 2:未登录 10:goodsId为空 11:未找到商品 12:已经添加到了购物车
     */
    @ResponseBody
    @PostMapping(value = "addCartGoods.do", params = {"goodsId","number"})
    public String addCartGoods(CartGoodsDTO cartGoodsDTO) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "f2";
        }
        try {
            return userHtmlService.addCartGoods(userId,cartGoodsDTO).toString();
        } catch (RuntimeException se) {
            return "f" + se.getMessage();
        }


        /*if (userId == null) {
            return -2;
        } else if (goodsService.selectByPrimaryKey(cartGoodsDTO.getGoodsId()) == null) {
            return -1;
        } else if (cartGoodsDTO.getGoodsId() == null) {
            return 0;
        } else if (cartGoodsService.selectByUserIdandGoodsId(userId, cartGoodsDTO.getGoodsId()) != 0) {
            return 2;
        }
        if (cartGoodsDTO.getNumber() !=null) {
            cartGoodsDTO.setNumber(cartGoodsDTO.getNumber() > 0 ? cartGoodsDTO.getNumber() : 1);
        } else {
            cartGoodsDTO.setNumber(1);
        }
        return cartGoodsService.insertByUserIdAndGoodsId(userId, cartGoodsDTO);*/
    }

    //收藏夹添加商品 -2请先登录 -1该商品失效 0添加失败 1添加成功 2已经添加
    @ResponseBody
    @PostMapping(value = "addCollectGoods.do", params = {"goodsId"})
    public int addCollectGoods(@RequestParam("goodsId") Long goodsId) {
        CollectGoodsDTO collectGoodsDTO = new CollectGoodsDTO();
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return -2;
        } else if (goodsService.selectByPrimaryKey(goodsId) == null) {
            return -1;
        } else if (goodsId == null) {
            return 0;
        } else if (collectGoodsService.selectByUserIdandGoodsId(userId, goodsId) != 0) {
            return 2;
        }
        return collectGoodsService.insertByUserIdAndGoodsId(userId, goodsId);
    }

    /**
     * @param myShopDTO
     * @return //1:修改成功 --- f+ 1:shopId为空 2:session中userId为空 3:类型不是数字 10:未找到shop 11:修改失败 12:没权限修改
     */
    @ResponseBody
    @PutMapping(value = "shopUpdate.do")
    public String shopUpdate(MyShopDTO myShopDTO) {
        if (session.getAttribute("userId") == null) {
            return "f2";
        }
        if (myShopDTO.getShopId() == null) {
            return "f1";
        }
        try {
            userHtmlService.shopUpdate(myShopDTO, (Long) session.getAttribute("adminId"));
            return "1";
        } catch (RuntimeException se) {
            return se.getMessage();
        }
    }

    /**
     * @param goodsDTO
     * @return //1:修改成功 --- f+ 1:goodsID为空 2:session中userId为空 3:类型不是数字 10:未找到goods 11:修改失败 12:未找到商店 13:该店铺还在审核
     */
    @ResponseBody
    @PutMapping(value = "goodsUpdate.do")
    public String goodsUpdate(GoodsDTO goodsDTO) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "f2";
        }
        if (goodsDTO.getType() == null) {
            return "f3";
        }
        if (goodsDTO.getGoodsId() == null) {
            return "f1";
        }
        try {
            userHtmlService.goodsUpdate(goodsDTO);
            return "1";
        } catch (RuntimeException se) {
            return se.getMessage();
        }
    }

    /**
     * @param receivingAddressDTO
     * @return //1:添加成功 --- f+ 1:信息填写不完整 2:session中没找到userId 3:手机不是数字 4:手机长度不对 5:邮政编码长度不对 10:未找到user 12:添加收货地址失败
     */
    @ResponseBody
    @PutMapping(value = "receivingAddressUpdate.do")
    public String receivingAddressUpdate(ReceivingAddressDTO receivingAddressDTO) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "f2";
        }
        receivingAddressDTO.setUserId(userId);
        if (!NumberUtils.isNumber(receivingAddressDTO.getPhone())) {
            return "f3";
        }
        if (StringUtils.isEmptyOrWhitespace(receivingAddressDTO.getReceivingName())
                || StringUtils.isEmptyOrWhitespace(receivingAddressDTO.getArea())
                || StringUtils.isEmptyOrWhitespace(receivingAddressDTO.getAddress())
                || StringUtils.isEmptyOrWhitespace(receivingAddressDTO.getPhone())
                || StringUtils.isEmptyOrWhitespace(receivingAddressDTO.getPostCode())) {
            return "f1";
        }
        if (receivingAddressDTO.getPhone().length() != 11) {
            return "f4";
        }
        if (receivingAddressDTO.getPostCode().length() != 6) {
            return "f5";
        }
        try {
            userHtmlService.receivingAddressUpdate(receivingAddressDTO);
            return "1";
        } catch (RuntimeException se) {
            return se.getMessage();
        }
    }

    /**
     * a
     *
     * @param applyShopDTO
     * @return //数字(申请的shopId):添加成功 --- f+ 1:商店名或面熟描述为空 10:未找到user 11:商店名已经存在 12:添加商店失败 13:添加仓库失败
     */
    @ResponseBody
    @PostMapping(value = "applyShop.do")
    public String applyShop(ApplyShopDTO applyShopDTO) {
        if (StringUtils.isEmptyOrWhitespace(applyShopDTO.getName()) || StringUtils.isEmptyOrWhitespace(applyShopDTO.getDescription())) {
            return "f1";
        }
        try {
            applyShopDTO.setUserId((Long) session.getAttribute("userId"));
            return userHtmlService.addShop(applyShopDTO).toString();
        } catch (RuntimeException ae) {
            return "f" + ae.getMessage();
        }
    }

    /**
     * @param goodsDTO
     * @return //1:添加成功 --- f+ 1:信息填写不完整 2:session中没找到userId 3:类型不是数字 10:未找到shop 11:自家商品名已经存在 12:添加商品失败 13:该店铺还在审核
     */
    @ResponseBody
    @PostMapping(value = "addGoods.do")
    public String addGoods(GoodsDTO goodsDTO) {
        if (session.getAttribute("userId") == null) {
            return "f2";
        }
        if (goodsDTO.getType() == null) {
            return "f3";
        }
        if (StringUtils.isEmptyOrWhitespace(goodsDTO.getName())
                || StringUtils.isEmptyOrWhitespace(goodsDTO.getImageUrl())
                || goodsDTO.getPrice() == null
                || goodsDTO.getType() == null) {
            return "f1";
        }
        try {
            userHtmlService.addGoods(goodsDTO);
            return "1";
        } catch (RuntimeException ae) {
            return "f" + ae.getMessage();
        }
    }

    /**
     * @param receivingAddressDTO
     * @return //1:添加成功 --- f+ 1:信息填写不完整 2:session中没找到userId 3:手机不是数字 4:手机长度不对 5:邮政编码长度不对 10:未找到user 12:添加收货地址失败
     */
    @ResponseBody
    @PostMapping(value = "addReceivingAddress.do")
    public String addReceivingAddress(ReceivingAddressDTO receivingAddressDTO) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "f2";
        }
        receivingAddressDTO.setUserId(userId);
        if (!NumberUtils.isNumber(receivingAddressDTO.getPhone())) {
            return "f3";
        }
        if (StringUtils.isEmptyOrWhitespace(receivingAddressDTO.getReceivingName())
                || StringUtils.isEmptyOrWhitespace(receivingAddressDTO.getArea())
                || StringUtils.isEmptyOrWhitespace(receivingAddressDTO.getAddress())
                || StringUtils.isEmptyOrWhitespace(receivingAddressDTO.getPhone())
                || StringUtils.isEmptyOrWhitespace(receivingAddressDTO.getPostCode())) {
            return "f1";
        }
        if (receivingAddressDTO.getPhone().length() != 11) {
            return "f4";
        }
        if (receivingAddressDTO.getPostCode().length() != 6) {
            return "f5";
        }
        try {
            userHtmlService.addReceivingAddress(receivingAddressDTO);
            return "1";
        } catch (RuntimeException ae) {
            return "f" + ae.getMessage();
        }
    }

    /**
     * @param shopId
     * @return // 0删除失败 1删除成功 2未登录 3.参数shopId为空
     */
    @ResponseBody
    @DeleteMapping(value = "deleteShop.do/{shopId}")
    public int deleteShop(@PathVariable("shopId") Long shopId) {
        if (shopId == null) {
            return 3;
        }
        if (session.getAttribute("userId") == null) {
            return 2;
        }
        return userHtmlService.deleteShop(shopId) ? 1 : 0;
    }

    /**
     * @param goodsId
     * @return // 0删除失败 1删除成功 2未登录 3.参数shopId为空
     */
    @ResponseBody
    @DeleteMapping(value = "deleteGoods.do/{goodsId}")
    public int deleteGoods(@PathVariable("goodsId") Long goodsId) {
        if (goodsId == null) {
            return 3;
        }
        if (session.getAttribute("userId") == null) {
            return 2;
        }
        return userHtmlService.deleteGoods(goodsId) ? 1 : 0;
    }

    /**
     * @param receivingAddressId
     * @return // 0删除失败 1删除成功 2未登录 3.参数shopId为空
     */
    @ResponseBody
    @DeleteMapping(value = "deleteReceivingAddress.do/{receivingAddressId}")
    public int deleteReceivingAddress(@PathVariable("receivingAddressId") Long receivingAddressId) {
        if (receivingAddressId == null) {
            return 3;
        }
        if (session.getAttribute("userId") == null) {
            return 2;
        }
        return userHtmlService.deleteReceivingAddress(receivingAddressId) ? 1 : 0;
    }

    /**
     * @param cartGoodsDTOList
     * @return // 数值>0为正确 --- -3没有商品 -1:参数数据和数据库比对不正确 -2:未登录
     */
    @ResponseBody
    @PostMapping(value = "payment.do")
    public double paymentAll(@RequestBody List<CartGoodsDTO> cartGoodsDTOList) {
        if (session.getAttribute("userId") == null) {
            return -2;
        }
        if (cartGoodsDTOList.size() == 0 || cartGoodsDTOList == null) {
            return -3;
        }
        try {
            return userHtmlService.compareToDBAndGetAll(cartGoodsDTOList);
        } catch (TransactionException te) {
            return -1;
        }
    }


    /**
     * @param cartGoodsDTOList
     * @return // 数值>0为正确 --- f+ 1:参数数据和数据库比对不正确 2:未登录 3没有商品 10:未找到用户 11:余额不足 13:未找到商品 16:商品已售完 12:扣款发生错误 14:未找到商品的店铺 15:店铺打款错误 17:订单添加失败
     */
    @ResponseBody
    @PostMapping(value = "pay.do")
    public String pay(@RequestBody List<CartGoodsDTO> cartGoodsDTOList) {
        System.out.println(cartGoodsDTOList);
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "f2";
        }
        if (cartGoodsDTOList.size() == 0 || cartGoodsDTOList == null) {
            return "f3";
        }
        try {
            userHtmlService.pay(userId, cartGoodsDTOList);
            return "" + userHtmlService.pay(userId, cartGoodsDTOList);
        } catch (TransactionException te) {
            return "f" + te.getMessage();
        }

    }




    @ResponseBody
    @PutMapping(value = "test.do")
    public String test(CartVO cartVO, BindingResult userloginResult) {
        System.out.println(cartVO);
        return "true";
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
    @RequestMapping(value = "/test333", method = RequestMethod.GET)
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
