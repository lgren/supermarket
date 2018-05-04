package com.lgren.action.user;

import com.lgren.action.user.dto.*;
import com.lgren.dao.ShopMapper;
import com.lgren.exception.TransactionException;
import com.lgren.pojo.dto.*;
import com.lgren.pojo.po.Shop;
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
import java.util.Map;
import java.util.Optional;

@Controller
public class UserIndexAction {
    @Autowired
    private Mapper mapper;
    @Autowired
    private UserHtmlService userHtmlService;
    @Autowired
    private ShopService shopService;


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

    @ResponseBody
    @RequestMapping(value = "isAuthCode.do", params = {"authCode"})
    public boolean authCodeVer(@RequestParam("authCode") String authCode) {
        return isAuthCode(authCode);
    }

    private boolean isAuthCode(String authCode) {
        return session.getAttribute("authCode").toString().equals(authCode.toLowerCase());
    }


    /**
     * @param userLoginDTO
     * @return //数字(userId)代表成功 ---f+ 0:用户名或密码错误 1用户名或密码为空 2已经登陆了 3验证码不正确
     */
    @ResponseBody
    @GetMapping(value = "userLogin.do")
    public String userLogin(UserLoginDTO userLoginDTO) {
        if (StringUtils.isEmptyOrWhitespace(userLoginDTO.getUsername())
                || StringUtils.isEmptyOrWhitespace(userLoginDTO.getPassword())) {
            return "f1";
        }
        if (!isAuthCode(userLoginDTO.getAuthCode())) {
            return "f3";
        }
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(userLoginDTO.getUsername(),
                    userLoginDTO.getPassword(), userLoginDTO.isAutoLogin());
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
     * @return // 数字:成功 --- f+* 0:参数问题 1账号或密码不能为空 2账号或密码输入格式不对 3:验证码不正确
     *              10:用户已存在 11:增加用户失败 12:未查找刚插入的用户 13:个人购物车增加失败 14:个人收藏夹添加失败
     */
    @ResponseBody
    @PostMapping(value = "registration.do")
    public String registration(UserRegistrationDTO userRegistrationDTO) {
        if (StringUtils.isEmptyOrWhitespace(userRegistrationDTO.getUsername())
                || StringUtils.isEmptyOrWhitespace(userRegistrationDTO.getPassword())) {
            return "f1";
        }
        if (!isAuthCode(userRegistrationDTO.getAuthCode())) {
            return "f3";
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
        userHtmlDTO.setPhone(null);
        userHtmlDTO.setEmail(null);
        userHtmlDTO.setMoney(null);
        if (userHtmlDTO.getUserId() == null) {
            return -1;
        }
        User user = mapper.map(userHtmlDTO, User.class);
        user.setGender("男".equals(userHtmlDTO.getGenderStr()) ? 1 : 0);
        return userService.updateByPrimaryKeySelective(user);
    }

    /**
     *
     * @param password
     * @param newPassword
     * @return //1:修改成功 --- f+ 0:session中没有userId 1:密码错误 2:修改失败
     */
    @ResponseBody
    @PutMapping(value = "changePassword.do")
    public String changePassword(@RequestParam("password") String password,
                                 @RequestParam("newPassword") String newPassword) {
//        User user = userService.selectByPrimaryKey((Long) session.getAttribute("userId"));
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "f0";
        }
        userId = userService.isByUserIdAndPassword(userId,password);
        if (userId == null) {
            return "f1";
        }
        User user = new User();
        user.setUserId(userId);
        user.setPassword(newPassword);
        if (userService.updateByPrimaryKeySelective(user) !=1 ) {
            return "f2";
        }
        return "1";
    }

    /**
     *
     * @param paymentPassword
     * @param newPaymentPassword
     * @return //1:修改成功 --- f+ 0:session中没有userId 1:支付密码错误 2:修改失败 3:长度不为6
     */
    @ResponseBody
    @PutMapping(value = "changePaymentPassword.do")
    public String changePaymentPassword(@RequestParam("paymentPassword") String paymentPassword,
                                 @RequestParam("newPaymentPassword") String newPaymentPassword) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "f0";
        }
        if (newPaymentPassword.length() != 6 || !NumberUtils.isNumber(newPaymentPassword)) {
            return "f3";
        }
        User user = userService.selectByPrimaryKey(userId);
        if (user.getPaymentPassword() == null || user.getPaymentPassword().equals("")){
        } else {
            if (!user.getPaymentPassword().equals(paymentPassword)) {
                return "f1";
            }
        }
        user.setPaymentPassword(newPaymentPassword);
        if (userService.updateByPrimaryKeySelective(user) !=1 ) {
            return "f2";
        }

        return "1";
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
    @DeleteMapping(value = "cartGoodsDelete.do")
    public String cartGoodsDelete(@RequestParam("goodsId") Long goodsId,
                                  @RequestParam("orderId") Long orderId,
                                  @RequestParam("type") Integer type) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "f";
        }
        if (goodsId == null) {
            return "0";
        }
        return "" + (userHtmlService.deleteCart(userId,goodsId,orderId,type) ? 1 : 0);
    }

    //购物车删除商品 0删除失败 1删除成功
    @ResponseBody
    @DeleteMapping(value = "cartGoodsDelete.do/{cartGoodsId}")
    public String cartGoodsDelete(@PathVariable("cartGoodsId") Long cartGoodsId) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "f";
        }
        return "" + cartGoodsService.deleteByPrimaryKey(cartGoodsId);
    }

    /**
     * @param cartGoodsDTO
     * @return //1:代表成功 0:代表失败 --- f+ 2:未登录 10:goodsId为空 11:未找到商品 12:已经添加到了购物车
     */
    @ResponseBody
    @PostMapping(value = "addCartGoods.do", params = {"goodsId", "number"})
    public String addCartGoods(CartGoodsDTO cartGoodsDTO) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "f2";
        }
        try {
            return userHtmlService.addCartGoods(userId, cartGoodsDTO).toString();
        } catch (RuntimeException se) {
            se.printStackTrace();
            return "f" + se.getMessage();
        }
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
     * @param addGoodsDTO
     * @return //1:修改成功 --- f+ 1:goodsID为空 2:session中userId为空 3:类型不是数字 10:未找到goods 11:修改失败 12:未找到商店 13:该店铺还在审核
     */
    @ResponseBody
    @PutMapping(value = "goodsUpdate.do")
    public String goodsUpdate(AddGoodsDTO addGoodsDTO) {
        addGoodsDTO.setDiscount(addGoodsDTO.getDiscount()/10);
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "f2";
        }
        if (addGoodsDTO.getType() == null) {
            return "f3";
        }
        if (addGoodsDTO.getGoodsId() == null) {
            return "f1";
        }
        try {
            userHtmlService.goodsUpdate(addGoodsDTO);
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
     * @return //数字(申请的shopId):添加成功 --- f+ 1:商店名或面熟描述为空 3:验证码错误 10:未找到user 11:商店名已经存在 12:添加商店失败 13:添加仓库失败
     */
    @ResponseBody
    @PostMapping(value = "applyShop.do")
    public String applyShop(ApplyShopDTO applyShopDTO) {
        if (StringUtils.isEmptyOrWhitespace(applyShopDTO.getName()) || StringUtils.isEmptyOrWhitespace(applyShopDTO.getDescription())) {
            return "f1";
        }
        if (!isAuthCode(applyShopDTO.getAuthCode())) {
            return "f3";
        }
        try {
            applyShopDTO.setUserId((Long) session.getAttribute("userId"));
            return userHtmlService.addShop(applyShopDTO).toString();
        } catch (RuntimeException ae) {
            return "f" + ae.getMessage();
        }
    }

    /**
     * @param addGoodsDTO
     * @return //1:添加成功 --- f+ 1:信息填写不完整 2:session中没找到userId 3:类型不是数字 4:验证码不正确
     *          10:未找到shop 11:自家商品名已经存在 12:添加商品失败 13:该店铺还在审核
     */
    @ResponseBody
    @PostMapping(value = "addGoods.do")
    public String addGoods(AddGoodsDTO addGoodsDTO) {
        if (session.getAttribute("userId") == null) {
            return "f2";
        }
        if (addGoodsDTO.getType() == null) {
            return "f3";
        }
        if (!isAuthCode(addGoodsDTO.getAuthCode())) {
            return "f4";
        }
        if (StringUtils.isEmptyOrWhitespace(addGoodsDTO.getName())
                || StringUtils.isEmptyOrWhitespace(addGoodsDTO.getImageUrl())
                || addGoodsDTO.getPrice() == null
                || addGoodsDTO.getType() == null) {
            return "f1";
        }
        try {
            userHtmlService.addGoods(addGoodsDTO);
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
     * @return // 0删除失败 1删除成功 2未登录 3.参数shopId为空 4有订单正在执行
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
        int result = 0;
        try {
            result = userHtmlService.deleteShop(shopId) ? 1 : 0;
        } catch (RuntimeException re) {
            re.printStackTrace();
            return 4;
        }
        return result;
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
     * @param paymentDTO
     * @return //1 支付成功 --- f+ 1:密码长度必须是6位且为数字 2:需要支付的钱不能为空 3:验证码不能为空 4:支付密码错误 10:未找到用户 11:余额不足 12:扣款发生错误
     *         //13:未找到订单 14:未找到店铺 15:未找到店铺仓库 16:库存不足 17:店铺加钱失败 18:仓库更新失败 19:订单更新失败 20:购物车删除失败
     */
    @ResponseBody
    @PostMapping(value = "/pay.do")
    public String pay(@RequestBody PaymentDTO paymentDTO) {
        Long userId = (Long) session.getAttribute("userId");
        if (paymentDTO.getPaymentPassword().length() != 6 || !NumberUtils.isNumber(paymentDTO.getPaymentPassword())) {
            return "f1";
        }
        if (paymentDTO.getAll() == null) {
            return "f2";
        }
        if (paymentDTO.getAuthCode() == null) {
            return "f3";
        }
        if (userService.payment(userId,paymentDTO.getPaymentPassword()) == null) {
            return "f4";
        }
        try {
            userHtmlService.pay(paymentDTO.getAll(), userId, paymentDTO.getPaymentPassword(),
                    paymentDTO.getOrderIdList(), paymentDTO.getCartGoodsIdList());
            return "1";
        } catch (RuntimeException re) {
            re.printStackTrace();
            return "f" + re.getMessage();
        }
    }

    /**
     *
     * @param orderId
     * @return //purchasedId ---  10:order(订单)更新失败 11:purchased(已购买)添加失败
     */
    @ResponseBody
    @PutMapping("/confirmGoods.do")
    public String confirmGoods(@RequestParam("orderId") Long orderId) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "f";
        }
        return "" + userHtmlService.confirmGoods(orderId);
    }

    @ResponseBody
    @PutMapping("/evaluationGoods.do")
    public String evaluationGoods(PurchasedDTO purchasedDTO) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "f";
        }
        return "" + userHtmlService.evaluationGoods(purchasedDTO);
    }

    /**
     *
     * @param orderId
     * @param sendGoodsId
     * @return //0失败 1成功 --- f+ null:未登录 0:orderId为空,1:sendGoodsId为空
     */
    @ResponseBody
    @PutMapping("/sendGoods.do")
    public String sendGoods(@RequestParam("orderId") Long orderId,
                            @RequestParam("sendGoodsId") Long sendGoodsId) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "f";
        }
        if (orderId == null) {
            return "f0";
        }
        if (sendGoodsId == null) {
            return "f1";
        }
        return "" + userHtmlService.sendGoods(orderId,sendGoodsId);
    }

    @ResponseBody
    @PutMapping(value = "/recharge.do")
    public int recharge(Map map,
            @RequestParam("money")Double money,
            @RequestParam("shopId")Long shopId) {
        Long userId = (Long) session.getAttribute("userId");
        if(userId == null) {
            return -1;
        }
        if (shopId != null) {
            Shop shop = shopService.selectByPrimaryKey(shopId);
            shop.setShopId(shopId);
            shop.setMoney((shop.getMoney() + money));
            return shopService.updateByPrimaryKeySelective(shop);
        }
        User user = userService.selectByPrimaryKey(userId);
        user.setMoney((user.getMoney() + money));
        user.setUserId(userId);
        map.put("shopId",null);
        return userService.updateByPrimaryKeySelective(user);
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
