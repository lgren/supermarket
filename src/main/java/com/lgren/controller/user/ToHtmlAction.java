package com.lgren.controller.user;

import com.lgren.api.moudle.CartApi;
import com.lgren.api.moudle.GoodsApi;
import com.lgren.api.moudle.UserApi;
import com.lgren.api.remote.ShopRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class ToHtmlAction {
    @Autowired
    private ShopRemote shopRemote;
    @Autowired
    private GoodsApi goodsApi;
    @Autowired
    private CartApi cartApi;
    @Autowired
    private UserApi userApi;
    @Autowired
    private HttpSession session;
    @GetMapping(value = "/toIndex")
    public String toIndex(Map<String,Object> map) {
        map.put("userId",session.getAttribute("userId"));
        map.put("goods",goodsApi.getAllGoods());
        map.put("shops",shopRemote.getAllShop());
        return "index";
    }
    @GetMapping(value = "/toRegistration")
    public String toRegistration() {
        return "registration";
    }
    @GetMapping(value = "/toLogin")
    public String toLogin() {
        return "login";
    }
    @GetMapping(value = "/toCart")
    public String toCart(Map<String,Object> map) {
        map.put("userId",session.getAttribute("userId"));
        map.put("cart",cartApi.getCartByUserId((Long) session.getAttribute("userId")));
        return "cart";
    }
    @RequestMapping(value = "/toCollect")
    public String toCollect() {
        return "collect";
    }
    @RequestMapping(value = "/toUser")
    public String toUser(Map<String,Object> map) {
        map.put("user",userApi.getUserById((Long) session.getAttribute("userId")));
        return "user";
    }
    @RequestMapping(value = "/toSeller")
    public String toSeller() {
        return "seller";
    }
}
