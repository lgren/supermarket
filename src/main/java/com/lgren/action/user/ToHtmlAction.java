package com.lgren.action.user;

import com.lgren.api.moudle.*;
import com.lgren.action.user.dto.UserHtmlDTO;
import com.lgren.dao.CartGoodsMapper;
import com.lgren.pojo.dto.OrderDTO;
import com.lgren.pojo.dto.UserDTO;
import com.lgren.pojo.po.Shop;
import com.lgren.pojo.vo.*;
import com.lgren.service.OrderService;
import com.lgren.service.ShopService;
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
    private ShopService shopService;
    @Autowired
    private CartGoodsMapper cartGoodsMapper;



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
    private OrderApi orderApi;
    @Autowired
    private PurchasedApi purchasedApi;
    @Autowired
    private ReceivingAddressApi receivingAddressApi;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpSession session;
    //总结  aop切到userId

    @GetMapping(value = {"/toIndex","/"})
    public String toIndex(Map<String, Object> map) {
//        PageHelper.startPage(1,2);
//        PageInfo<GoodsVO> pageInfo = new PageInfo<GoodsVO>();
        map.put("goods", goodsApi.getAllGoods());
//        PageHelper.startPage(1,2);
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
    public String toCart(Map<String, Object> map) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "login";
        }
        CartVO cartVO = cartApi.getCartGoodsByUserIdAndType(userId,0);
        map.put("userId", userId);
        map.put("cart", cartVO);
        return "cart";
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
            List<GoodsVO> goodsVOList = userHtmlService.getGoodsByShopId(shopId,(Long) session.getAttribute("userId"));
            map.put("goodsVOList", goodsVOList);
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
            Map getOrderReturn = userHtmlService.getOrder(userId,cartVO);
            map.put("all", getOrderReturn.get("all"));
            map.put("orderIdList", getOrderReturn.get("orderIdList"));
            map.put("cartGoodsIdList", cartVO.getCartGoodsVOList().stream()
                    .map(cartGoodsVO -> cartGoodsVO.getCartGoodsId()).collect(Collectors.toList()));
            map.put("requestURL",request.getHeader("Referer"));
            return "payment";
        } catch (RuntimeException re) {
            /*if (re.getMessage() == "19") {
                return "redirect:toCart";
            }*/
            re.printStackTrace();
            map.put("exception" , re.getMessage());
            return "notNormal";
        }
    }

    @GetMapping(value = "/toPay",params = {"orderId"})
    public String toPay(Map map, @RequestParam("orderId") Long orderId){
        Long userId = (Long) session.getAttribute("userId");
        UserDTO userDTO = mapper.map(userService.selectByPrimaryKey(userId),UserDTO.class);
        if (userDTO.getPaymentPassword() == null) {
            return "user";
        }
        if (userId == null) {
            return "login";
        }
        OrderDTO orderDTO = mapper.map(orderService.selectByPrimaryKey(orderId),OrderDTO.class);
        map.put("all",orderDTO.getAmount()*orderDTO.getPrice());
        List<Long> orderIdList = new ArrayList();
        orderIdList.add(orderDTO.getOrderId());
        map.put("orderIdList",orderIdList);
        List<Long> cartGoodsIdList = new ArrayList<>();
        Long cartGoodsId = cartGoodsMapper.getCartGoodsByWantPayTime(orderDTO.getOrderTime().getTime());
        cartGoodsIdList.add(cartGoodsId);
        map.put("cartGoodsIdList",cartGoodsIdList);
        map.put("requestURL",request.getHeader("Referer"));
        return "payment";
    }

    @GetMapping(value = "/toOrder")
    public String toOrder(Map<String, Object> map) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "login";
        }
        CartVO cartVO = cartApi.getCartGoodsByUserIdAndType(userId,1);
        map.put("userId", userId);
        List<OrderVO> orderVOList = orderApi.getOrderByUserId(userId);
        map.put("orderVOListByUserId", orderVOList);
        List<PurchasedVO> purchasedVOList = purchasedApi.getPurchasedVOListByUserId(userId);
        map.put("purchasedVOList", purchasedVOList);
        map.put("cart", cartVO);

        return "myOrder";
    }

    @GetMapping(value = "/toMyShopOrder/{shopId}")
    public String toMyShopOrder(Map<String, Object> map,@PathVariable("shopId") Long shopId) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "login";
        }
        List<Shop> shopList = shopService.getShopByUserId(userId);
        if (shopList != null) {
            if(shopList.stream().filter(shop -> shop.getUserId() == userId).collect(Collectors.toList()) == null){
                return "notFindShop";
            }
        }
        map.put("userId", userId);
        List<OrderVO> orderVOList = orderApi.getOrderByShopId(shopId);
        map.put("orderVOListByUserId", orderVOList);
        List<PurchasedVO> purchasedVOList = purchasedApi.getPurchasedVOListByShopId(shopId);
        map.put("purchasedVOList", purchasedVOList);
//        map.put("cart", cartVO);

        return "myShopOrder";
    }

    @GetMapping(value = "/toRecharge")
    public String recharge(Map map, @RequestParam("shopId") Long shopId) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "login";
        }
        map.put("shopId",shopId);
        return "recharge";
    }

    @RequestMapping(value = "/toSeller")
    public String toSeller() {
        return "seller";
    }



}
