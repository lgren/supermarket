package com.lgren.controller.user;

import com.lgren.api.moudle.*;
import com.lgren.controller.user.dto.UserHtmlDTO;
import com.lgren.pojo.dto.OrderDTO;
import com.lgren.pojo.dto.UserDTO;
import com.lgren.pojo.vo.CartVO;
import com.lgren.pojo.vo.GoodsVO;
import com.lgren.pojo.vo.ShopVO;
import com.lgren.service.OrderService;
import com.lgren.service.UserHtmlService;
import com.lgren.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ToHtmlAction {
    @Autowired
    private Mapper mapper;
    @Autowired
    private UserHtmlService userHtmlService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;



    @Autowired
    private ShopApi shopApi;
    @Autowired
    private GoodsApi goodsApi;
    @Autowired
    private CartApi cartApi;
    @Autowired
    private CollectApi collectApi;
    @Autowired
    private UserApi userApi;
    @Autowired
    private ReceivingAddressApi receivingAddressApi;
    @Autowired
    private CartGoodsApi cartGoodsApi;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpSession session;
    //总结  aop切到userId

    @GetMapping(value = "/toIndex")
    public String toIndex(Map<String, Object> map) {
        map.put("goods", goodsApi.getAllGoods());
        map.put("shops", shopApi.getAllShopVO());
        return "index";
    }

    @GetMapping(value = "/toRegistration")
    public String toRegistration() {
        return "registration";
    }

    @GetMapping(value = "/toLogin")
    public String toLogin(Map<String, Object> map) {
        map.put("requestURL",request.getHeader("Referer"));
        return "login";
    }

    @GetMapping(value = "/toCart")
    public String toCart(Map<String, Object> map,Integer type) {
        CartVO cartVO = cartApi.getCartGoodsByUserIdAndType((Long) session.getAttribute("userId"),type == null ? 0 : type);
        map.put("userId", session.getAttribute("userId"));
        map.put("cart", cartVO);
        if (type == null || type == 0) {
            return "cart";
        } else {
            return "myOrder";
        }
    }

    @GetMapping(value = "/toCollect")
    public String toCollect(Map<String, Object> map) {
        map.put("userId", session.getAttribute("userId"));
        map.put("collect", collectApi.getCollectByUserId((Long) session.getAttribute("userId")));
        return "collect";
    }

    @GetMapping(value = "/toUser")
    public String toUser(Map<String, Object> map) {
        Long userId = (Long) session.getAttribute("userId");
        UserDTO userDTO = userApi.getUserById(userId);
        UserHtmlDTO userHtmlDTO = new UserHtmlDTO();
        if (userDTO != null) {
            userHtmlDTO = mapper.map(userDTO, UserHtmlDTO.class);
            userHtmlDTO.setGenderStr(userDTO.getGender() == 1 ? "男" : "女");
        }
        map.put("user", userHtmlDTO);
        map.put("receivingAddressVOList", receivingAddressApi.getReceivingAddressVOListByUserId(userId));
        return "user";
    }

    @GetMapping(value = "/toGoods/{goodsId}")
    public String toGoods(Map<String, Object> map, @PathVariable("goodsId") Long goodsId) {
        if (goodsId == null) {
            return "notFindGoods";
        }
        GoodsVO goodsVO = goodsApi.getGoodsById(goodsId);
        if (goodsVO == null) {
            return "notFindGoods";
        }
        map.put("goods", goodsVO);
        return "goods";
    }

    @GetMapping(value = "/toSeller")
    public String toSeller(Map<String, Object> map) {
        map.put("shops", shopApi.getAllShopVO());
        return "seller";
    }

    @GetMapping(value = "/toMyShop")
//    @RequiresRoles("seller")
    public String toMyShop(Map<String, Object> map) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:toLogin";
        }
        Subject subject = SecurityUtils.getSubject();
        if (!subject.hasRole("seller")) {
            return "notFindShop";
        }
        List<ShopVO> shopVOList = userHtmlService.getMyShopByUserId(userId);
        if (shopVOList == null) {
            shopVOList.add(new ShopVO());
        }
        map.put("myShopVOList",shopVOList);
        return "myShop";
    }

    /**
     * 处理异常 10:找不到商店 11:查询商品异常
     * @param map
     * @param shopId
     * @return
     */
    @GetMapping(value = "/toShop/{shopId}")
    public String toShop(Map<String, Object> map, @PathVariable("shopId") Long shopId) {
        ShopVO shopVO = shopApi.getShopById(shopId);
        if (shopVO == null) {
            return "notFindShop";
        }
        map.put("shopVO", shopVO);
        try {
            map.put("goodsVOList", userHtmlService.getGoodsByShopId(shopId));
        } catch (RuntimeException re) {
            switch (re.getMessage()) {
                case "10" : return "notFindShop";
                default: break;
            }
        }
        return "shop";
    }

    /**
     * 处理异常 10:找不到商店 11:查询商品异常 12:该用户与商铺负责人不匹配
     * @param map
     * @param shopId
     * @return
     */
    @GetMapping(value = "/toMyShopGoods/{shopId}")
    public String toMyShopGoods(Map<String, Object> map, @PathVariable("shopId") Long shopId) {
        ShopVO shopVO = shopApi.getShopById(shopId);
        if (shopVO == null) {
            return "notFindShop";
        }
        map.put("shopVO", shopVO);
        try {
            map.put("goodsVOList", userHtmlService.getGoodsByShopId(shopId,(Long) session.getAttribute("userId")));
        } catch (RuntimeException re) {
            switch (re.getMessage()) {
                case "12" : return "redirect:../toMyShop";
                case "10" : return "notFindShop";
                default: break;
            }
        }
        return "myShopGoods";
    }

    @GetMapping(value = "/toApplyShop")
    public String toShop() {
        return "applyShop";
    }

    /**
     *
     * @param map
     * @param cartVO
     * @param bindingResult
     * @return 10:未找到用户 13:未找到商品 14:未找到商品的店铺 16:商品已售完 XXX的库存不够 17:订单添加失败 18:购物车移除失败 19:订单已经存在
     */
    @PostMapping(value = "/toPay")
    public String toPay(Map map, CartVO cartVO, BindingResult bindingResult) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "login";
        }
        UserDTO userDTO = mapper.map(userService.selectByPrimaryKey(userId),UserDTO.class);
        if (userDTO.getPaymentPassword() == null || userDTO.getPaymentPassword().equals("")) {
            return "redirect:toUser";
        }
        try {
//            TransactionUserAndShopDTO transactionUserAndShopDTO = userHtmlService.getOrder(userId,cartVO);
            Map getOrderReturn = userHtmlService.getOrder(userId,cartVO);
            map.put("all", getOrderReturn.get("all"));
            map.put("orderIdList", getOrderReturn.get("orderIdList"));
            map.put("cartGoodsIdList", cartVO.getCartGoodsVOList().stream()
                    .map(cartGoodsVO -> cartGoodsVO.getCartGoodsId()).collect(Collectors.toList()));
            return "payment";
        } catch (RuntimeException re) {
            /*if (re.getMessage() == "19") {
                return "redirect:toCart";
            }*/
            map.put("exception" , re.getMessage());
            return "notNormal";
        }
    }

    @GetMapping(value = "/toPay",params = {"cartGoodsId", "goodsId"})
    public String toPay(Map map,
                        @RequestParam("cartGoodsId") Long cartGoodsId,
                        @RequestParam("goodsId") Long goodsId){
        Long userId = (Long) session.getAttribute("userId");
        UserDTO userDTO = mapper.map(userService.selectByPrimaryKey(userId),UserDTO.class);
        if (userDTO.getPaymentPassword() == null) {
            return "user";
        }
        if (userId == null) {
            return "login";
        }
        OrderDTO orderDTO = mapper.map(orderService.getOrderByUserIdAndgoodsId(userId, goodsId),OrderDTO.class);
        map.put("all",orderDTO.getAmount()*orderDTO.getPrice());
        List<Long> orderIdList = new ArrayList();
        orderIdList.add(orderDTO.getOrderId());
        map.put("orderIdList",orderIdList);
        List<Long> cartGoodsIdList = new ArrayList<>();
        cartGoodsIdList.add(cartGoodsId);
        map.put("cartGoodsIdList",orderIdList);
        return "payment";
    }









    @RequestMapping(value = "/toSeller")
    public String toSeller() {
        return "seller";
    }
}
