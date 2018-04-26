package com.lgren.controller.user;

import com.lgren.api.moudle.*;
import com.lgren.controller.user.dto.UserHtmlDTO;
import com.lgren.pojo.dto.UserDTO;
import com.lgren.pojo.vo.GoodsVO;
import com.lgren.pojo.vo.ShopVO;
import com.lgren.service.UserHtmlService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class ToHtmlAction {
    @Autowired
    private Mapper mapper;
    @Autowired
    private UserHtmlService userHtmlService;

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
    public String toCart(Map<String, Object> map) {
        map.put("userId", session.getAttribute("userId"));
        map.put("cart", cartApi.getCartByUserId((Long) session.getAttribute("userId")));
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
        UserDTO userDTO = userApi.getUserById((Long) session.getAttribute("userId"));
        UserHtmlDTO userHtmlDTO = new UserHtmlDTO();
        if (userDTO != null) {
            userHtmlDTO = mapper.map(userDTO, UserHtmlDTO.class);
            userHtmlDTO.setGenderStr(userDTO.getGender() == 1 ? "男" : "女");
        }
        map.put("user", userHtmlDTO);
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
    public String toMyShop(Map<String, Object> map) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:toLogin";
        }
        map.put("myShopVOList",userHtmlService.getMyShopByUserId(userId));
        return "myShop";
    }

    @GetMapping(value = "/toShop/{shopId}")
    public String toShop(Map<String, Object> map, @PathVariable("shopId") Long shopId) {
        ShopVO shopVO = shopApi.getShopById(shopId);
        if (shopVO == null) {
            return "notFindGoods";
        }
        map.put("shopVO", shopVO);
        map.put("goodsVOList", userHtmlService.getGoodsByShopId(shopId));
        return "shop";
    }










    @RequestMapping(value = "/toSeller")
    public String toSeller() {
        return "seller";
    }
}
