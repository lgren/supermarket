package com.lgren.action;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lgren.dao.GoodsMapper;
import com.lgren.pojo.po.Goods;
import com.lgren.util.VerifyCodeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommonAction {
    private Logger logger = LoggerFactory.getLogger(CommonAction.class);
    @Autowired
    private HttpSession session;

    @Autowired
    private GoodsMapper goodsMapper;

    @RequestMapping(value="authCode.do",method=RequestMethod.GET)
    public void getYzm(HttpServletResponse response){
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");

            //生成随机字串
            String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
            //存入会话session
//            HttpSession session = request.getSession(true);
            session.setAttribute("authCode", verifyCode.toLowerCase());
            //生成图片
            int w = 146, h = 33;
            VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
        } catch (Exception e) {
            logger.error("生成验证码错误"+e.getMessage());
        }
    }

    @ResponseBody
    @GetMapping(value = "/test")
    public String test() {
        PageHelper.startPage(1,2);
        List<Goods> goodsList = goodsMapper.selectAll();
        PageInfo<Goods> goodsPageInfo = new PageInfo<>(goodsList);
        return "test";
    }










}
