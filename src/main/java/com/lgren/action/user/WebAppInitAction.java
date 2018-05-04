package com.lgren.action.user;

import com.alibaba.fastjson.JSONObject;
import com.lgren.api.moudle.*;
import com.lgren.dao.CartGoodsMapper;
import com.lgren.service.OrderService;
import com.lgren.service.ShopService;
import com.lgren.service.UserHtmlService;
import com.lgren.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class WebAppInitAction {
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

    private boolean hasRoles(String role) {
        Subject subject = SecurityUtils.getSubject();
        return subject.hasRole(role);
    }

    private Long hasLogin() {
        return (Long) session.getAttribute("userId");
    }

    @GetMapping(value = "/toIndex.do")
    public JSONObject test() {
        JSONObject indexInitData = new JSONObject();
        indexInitData.put("hasLogin",hasLogin());
        indexInitData.put("hasLogin",hasLogin());
        indexInitData.put("goodsVOList",goodsApi.getAllGoods());
        indexInitData.put("shopVOList",shopApi.getAllShopVO());
        System.out.println(indexInitData);

        return indexInitData;
    }


}
