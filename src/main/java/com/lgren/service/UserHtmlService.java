package com.lgren.service;

import com.lgren.action.user.dto.AddGoodsDTO;
import com.lgren.action.user.dto.ApplyShopDTO;
import com.lgren.action.user.dto.MyShopDTO;
import com.lgren.action.user.dto.UserRegistrationDTO;
import com.lgren.exception.*;
import com.lgren.pojo.dto.CartGoodsDTO;
import com.lgren.pojo.dto.PurchasedDTO;
import com.lgren.pojo.dto.ReceivingAddressDTO;
import com.lgren.pojo.vo.CartVO;
import com.lgren.pojo.vo.GoodsVO;
import com.lgren.pojo.vo.ShopVO;

import java.util.List;
import java.util.Map;

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

    /**
     * @param cartGoodsDTO
     * @return 1为成功 0为失败
     * @throws SelectException <br/>
     *                         10:goodsId为空
     *                         11:未找到商品
     *                         12:已经添加到了购物车
     */
    Long addCartGoods(Long userId, CartGoodsDTO cartGoodsDTO) throws SelectException;


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
     * @param addGoodsDTO
     * @return
     * @throws SelectException <br/>
     *                         10:未找到goods<br/>
     *                         12:未找到商店<br/>
     *                         13:该店铺还在审核<br/>
     * @throws UpdateException <br/>
     *                         11:修改失败<br/>
     */
    void goodsUpdate(AddGoodsDTO addGoodsDTO) throws SelectException, UpdateException;

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
     * @param addGoodsDTO
     * @throws SelectException <br/>
     *                         10:未找到shop<br/>
     *                         11:自家商品名已经存在<br/>
     * @throws AddException    <br/>
     *                         12:添加商品失败<br/>
     */
    void addGoods(AddGoodsDTO addGoodsDTO) throws SelectException, AddException;

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

    boolean deleteCart(Long userId,Long goodsId,Long orderId, Integer type);

    boolean deleteReceivingAddress(Long receivingAddressId);

    /**
     * @param cartGoodsDTOList
     * @return
     * @throws TransactionException <br/>
     *                              1:参数数据和数据库比对不正确
     */
    double compareToDBAndGetAll(List<CartGoodsDTO> cartGoodsDTOList) throws TransactionException;

    /**
     * @param userId
     * @param cartVO
     * @return 用户余额
     * @throws SelectException <br/>
     *                         10:未找到用户
     *                         13:未找到商品
     *                         16:商品已售完
     *                         XXX的库存不够
     *                         19:订单已经存在
     * @throws UpdateException <br/>
     *                         14:未找到商品的店铺
     * @throws AddException    <br/>
     *                         17:订单添加失败
     * @throws DeleteException <br/>
     *                         18:购物车移除失败
     */
    Map getOrder(Long userId, CartVO cartVO) throws DeleteException, SelectException, AddException;

    /**
     * @param userId
     * @param paymentPassword
     * @return 用户余额
     * @throws SelectException <br/>
     *                         10:未找到用户
     *                         11:余额不足
     *                         13:未找到订单
     *                         14:未找到店铺
     *                         15:未找到店铺仓库
     * @throws UpdateException <br/>
     *                         12:扣款发生错误
     *                         16:库存不足
     *                         17:店铺加钱失败
     *                         18:仓库更新失败
     *                         19:订单更新失败
     * @throws DeleteException <br/>
     *                         20:购物车删除失败
     */
    Boolean pay(Double all, Long userId, String paymentPassword, List<Long> orderIdList, List<Long> cartGoodsIdList) throws DeleteException, SelectException, UpdateException;

    /**
     * @param orderId
     * @return purchasedId
     * @throws UpdateException <br/>
     *                         10:order(订单)更新失败
     * @throws AddException    <br/>
     *                         11:purchased(已购买)添加失败
     */
    Long confirmGoods(Long orderId) throws UpdateException, AddException;

    int sendGoods(Long orderId, Long sendGoodsId);

    /**
     *
     * @param purchasedDTO
     * @return //0代表失败,1代表成功
     */
    int evaluationGoods(PurchasedDTO purchasedDTO) throws UpdateException;
}