package com.lgren.service;

import com.lgren.controller.user.dto.ApplyShopDTO;
import com.lgren.controller.user.dto.MyShopDTO;
import com.lgren.controller.user.dto.UserRegistrationDTO;
import com.lgren.exception.AddException;
import com.lgren.exception.SelectException;
import com.lgren.exception.UpdateException;
import com.lgren.pojo.dto.GoodsDTO;
import com.lgren.pojo.dto.ReceivingAddressDTO;
import com.lgren.pojo.vo.GoodsVO;
import com.lgren.pojo.vo.ShopVO;

import java.util.List;

public interface UserHtmlService {
    /**
     * @param userRegistrationDTO
     * @return
     * @throws AddException <br/>
     *                      10:用户已存在 <br/>
     *                      12:未查找刚插入的用户 <br/>
     * @throws AddException <br/>
     *                      11:增加用户失败 <br/>
     *                      13:个人购物车增加失败 <br/>
     *                      14:个人收藏夹添加失败
     */
    Long addUser(UserRegistrationDTO userRegistrationDTO) throws AddException;

    List<ShopVO> getMyShopByUserId(Long userId);

    /**
     * @param myShopDTO
     * @return
     * @throws SelectException <br/>
     *                         10:未找到shop<br/>
     *                         12:没权限修改<br/>
     * @throws UpdateException <br/>
     *                         11:修改失败<br/>
     */
    void shopUpdate(MyShopDTO myShopDTO, Long adminId) throws SelectException, UpdateException;

    /**
     * @param goodsDTO
     * @return
     * @throws SelectException <br/>
     *                         10:未找到goods<br/>
     *                         12:未找到商店<br/>
     *                         13:该店铺还在审核<br/>
     * @throws UpdateException <br/>
     *                         11:修改失败<br/>
     */
    void goodsUpdate(GoodsDTO goodsDTO) throws SelectException, UpdateException;

    /**
     * @param receivingAddressDTO
     * @return
     * @throws SelectException <br/>
     *                         10:未找到receivingAddress<br/>
     * @throws UpdateException <br/>
     *                         11:修改失败<br/>
     */
    void receivingAddressUpdate(ReceivingAddressDTO receivingAddressDTO) throws SelectException, UpdateException;


    /**
     * @param shopId
     * @return
     * @throws SelectException <br/>
     *                         10:找不到商店 <br/>
     *                         11:查询商品异常 <br/>
     */
    List<GoodsVO> getGoodsByShopId(Long shopId) throws SelectException;

    /**
     * @param shopId
     * @return
     * @throws SelectException <br/>
     *                         10:找不到商店 <br/>
     *                         11:查询商品异常 <br/>
     *                         12:该用户与商铺负责人不匹配 <br/>
     */
    List<GoodsVO> getGoodsByShopId(Long shopId, Long userId) throws SelectException;

    /**
     * @param applyShopDTO
     * @return 商店Id shopId
     * @throws SelectException <br/>
     *                         10:未找到user<br/>
     *                         11:商店名已经存在<br/>
     * @throws AddException    <br/>
     *                         12:添加商店失败<br/>
     */
    Long addShop(ApplyShopDTO applyShopDTO) throws SelectException, AddException;

    /**
     * @param goodsDTO
     * @throws SelectException <br/>
     *                         10:未找到shop<br/>
     *                         11:自家商品名已经存在<br/>
     *                         13:该店铺还在审核<br/>
     * @throws AddException    <br/>
     *                         12:添加商品失败<br/>
     */
    /**
     * @param goodsDTO
     * @throws SelectException <br/>
     *                         10:未找到shop<br/>
     *                         11:自家商品名已经存在<br/>
     * @throws AddException    <br/>
     *                         12:添加商品失败<br/>
     */
    void addGoods(GoodsDTO goodsDTO) throws SelectException, AddException;

    /**
     * @param receivingAddressDTO
     * @throws SelectException <br/>
     *                         10:未找到user<br/>
     * @throws AddException    <br/>
     *                         12:添加收货地址失败<br/>
     */
    void addReceivingAddress(ReceivingAddressDTO receivingAddressDTO) throws SelectException, AddException;

    boolean deleteShop(Long shopId);

    boolean deleteGoods(Long goodsId);

    boolean deleteReceivingAddress(Long receivingAddressId);
}