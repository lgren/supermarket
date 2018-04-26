package com.lgren.service;

import com.lgren.controller.user.dto.MyShopDTO;
import com.lgren.controller.user.dto.UserLoginDTO;
import com.lgren.exception.AddException;
import com.lgren.exception.SelectException;
import com.lgren.pojo.vo.ShopVO;

import java.util.List;

public interface UserHtmlService {
    /**
     *
     * @param userLoginDTO
     * @return
     * @throws AddException <br/>
     *         10:用户已存在 <br/>
     *         11:增加用户失败 <br/>
     *         12:未查找刚插入的用户 <br/>
     *         13:个人购物车增加失败 <br/>
     *         14:个人收藏夹添加失败
     */
    Long addUser(UserLoginDTO userLoginDTO) throws AddException;

    List<ShopVO> selectMyShopByUserId(Long userId);

    /**
     *
     * @param myShopDTO
     * @return
     * @throws SelectException <br/>
     *      10:未找到shop<br/>
     *      11:修改失败<br/>
     */
    void shopUpdate(MyShopDTO myShopDTO) throws SelectException;

}