package com.lgren.serviceImpl;

import com.lgren.api.moudle.GoodsApi;
import com.lgren.api.moudle.ShopApi;
import com.lgren.controller.user.dto.*;
import com.lgren.dao.*;
import com.lgren.exception.*;
import com.lgren.pojo.dto.CartGoodsDTO;
import com.lgren.pojo.dto.ReceivingAddressDTO;
import com.lgren.pojo.po.*;
import com.lgren.pojo.vo.CartGoodsVO;
import com.lgren.pojo.vo.CartVO;
import com.lgren.pojo.vo.GoodsVO;
import com.lgren.pojo.vo.ShopVO;
import com.lgren.service.UserHtmlService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserHtmlServiceImpl implements UserHtmlService {
    @Autowired
    private Mapper mapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private CollectMapper collectMapper;
    @Autowired
    private ReceivingAddressMapper receivingAddressMapper;
    @Autowired
    private WarehouseMapper warehouseMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CartGoodsMapper cartGoodsMapper;
    @Autowired
    private PurchasedMapper purchasedMapper;
    @Autowired
    private GoodsApi goodsApi;
    @Autowired
    private ShopApi shopApi;

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
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Long addUser(UserRegistrationDTO userRegistrationDTO) throws SelectException, AddException {
        //查找用户id
        User user = userMapper.getUserByUsername(userRegistrationDTO.getUsername());
        if (user != null) {
            throw new SelectException("10");
        }
        //增加用户
        if (userMapper.insertSelective(mapper.map(userRegistrationDTO, User.class)) != 1) {
            throw new AddException("11");
        }
        //查找刚添加的用户
        user = userMapper.getUserByUsername(userRegistrationDTO.getUsername());
        if (user == null) {
            throw new SelectException("12");
        }
        //增加cart和collect
        Cart cart = new Cart();
        Collect collect = new Collect();
        cart.setUserId(user.getUserId());
        collect.setUserId(user.getUserId());
        if (cartMapper.insertSelective(cart) != 1) {
            throw new AddException("13");
        }
        if (collectMapper.insertSelective(collect) != 1) {
            throw new AddException("14");
        }
        return user.getUserId();
    }

    /**
     * @param cartGoodsDTO
     * @return 1为成功 0为失败
     * @throws SelectException <br/>
     *                         10:goodsId为空
     *                         11:未找到商品
     *                         12:已经添加到了购物车
     */
    @Override
    public Long addCartGoods(Long userId, CartGoodsDTO cartGoodsDTO) throws SelectException {
        cartGoodsDTO.setCartId(cartMapper.getCartByUserId(userId).getCartId());
        if (cartGoodsDTO.getGoodsId() == null) {
            throw new SelectException("10");
        } else if (goodsMapper.selectByPrimaryKey(cartGoodsDTO.getGoodsId()) == null) {
            throw new SelectException("11");
        } else if (cartGoodsMapper.selectByCartIdandGoodsId(cartGoodsDTO.getCartId(), cartGoodsDTO.getGoodsId()) > 0) {
            throw new SelectException("12");
        }
        if (cartGoodsDTO.getNumber() != null) {
            cartGoodsDTO.setNumber(cartGoodsDTO.getNumber() > 0 ? cartGoodsDTO.getNumber() : 1);
        } else {
            cartGoodsDTO.setNumber(1);
        }
        cartGoodsDTO.setCartGoodsTime(new Date(System.currentTimeMillis()));
        cartGoodsDTO.setType(0);
        return Long.valueOf(cartGoodsMapper.insert(mapper.map(cartGoodsDTO, CartGoods.class)));
    }

    @Override
    public List<ShopVO> getMyShopByUserId(Long userId) {
        return shopApi.getShopVOListByUserId(userId);
    }

    /**
     * @param myShopDTO
     * @return
     * @throws SelectException <br/>
     *                         10:未找到shop<br/>
     *                         11:修改失败<br/>
     *                         12:没权限修改<br/>
     */
    @Override
    public void shopUpdate(MyShopDTO myShopDTO, Long adminId) throws SelectException, UpdateException {
        Shop shop = shopMapper.selectByPrimaryKey(myShopDTO.getShopId());
        if (shop.getState() == 0 && adminId == null) {
            throw new SelectException("12");
        }
        if (shop == null) {
            throw new SelectException("10");
        }
        shop = mapper.map(myShopDTO, Shop.class);
        if (shopMapper.updateByPrimaryKeySelective(shop) < 1) {
            throw new UpdateException("11");
        }
    }

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
    @Override
    public void goodsUpdate(AddGoodsDTO addGoodsDTO) throws SelectException, UpdateException {
        //查找商店
        Shop shop = shopMapper.selectByPrimaryKey(addGoodsDTO.getShopId());
        if (shop == null) {
            throw new SelectException("12");
        }
        if (warehouseMapper.updateNumberByShopIdAndGoodsId(shop.getShopId(), addGoodsDTO.getGoodsId(), addGoodsDTO.getNumber()) != 1) {
            throw new SelectException("12");
        }

        if (shop.getState() == 0) {
            throw new SelectException("13");
        }

        Goods goods = goodsMapper.selectByGoodsIdAndShopId(addGoodsDTO.getGoodsId(), addGoodsDTO.getShopId());
        if (goods == null) {
            throw new SelectException("10");
        }
        if (addGoodsDTO.getDiscount() == null || addGoodsDTO.getDiscount() > 10 || addGoodsDTO.getDiscount() < 0) {
            addGoodsDTO.setDiscount(10D);
        }
        if (Integer.valueOf(addGoodsDTO.getType()) < 0) {
            addGoodsDTO.setType(0);
        }
        goods = mapper.map(addGoodsDTO, Goods.class);
        if (goodsMapper.updateByPrimaryKeySelective(goods) < 1) {
            throw new UpdateException("11");
        }
    }

    /**
     * @param receivingAddressDTO
     * @return
     * @throws SelectException <br/>
     *                         10:未找到receivingAddress<br/>
     * @throws UpdateException <br/>
     *                         11:修改失败<br/>
     */
    @Override
    public void receivingAddressUpdate(ReceivingAddressDTO receivingAddressDTO) throws SelectException, UpdateException {
        ReceivingAddress receivingAddress = receivingAddressMapper.selectByPrimaryKey(receivingAddressDTO.getReceivingAddressId());
        if (receivingAddress == null) {
            throw new SelectException("10");
        }
        receivingAddress = mapper.map(receivingAddressDTO, ReceivingAddress.class);
        if (receivingAddressMapper.updateByPrimaryKeySelective(receivingAddress) < 1) {
            throw new UpdateException("11");
        }
    }

    /**
     * @param shopId
     * @return
     * @throws SelectException <br/>
     *                         10:找不到商店 <br/>
     *                         11:查询商品异常 <br/>
     */
    @Override
    public List<GoodsVO> getGoodsByShopId(Long shopId) throws SelectException {
        Shop shop = shopMapper.selectByPrimaryKey(shopId);
        if (shop == null) {
            throw new SelectException("10");
        }
        List<Goods> goodsList = goodsMapper.getGoodsByShopId(shopId);
        if (goodsList == null) {
            throw new SelectException("11");
        }
        return goodsApi.getGoodsVOList(goodsList);
    }

    /**
     * @param shopId
     * @return
     * @throws SelectException <br/>
     *                         10:找不到商店 <br/>
     *                         11:查询商品异常 <br/>
     *                         12:该用户与商铺负责人不匹配 <br/>
     */
    @Override
    public List<GoodsVO> getGoodsByShopId(Long shopId, Long userId) throws SelectException {
        Shop shop = shopMapper.selectByPrimaryKey(shopId);
        if (shop == null) {
            throw new SelectException("10");
        }
        if (shop.getUserId() != userId) {
            throw new SelectException("12");
        }
        List<Goods> goodsList = goodsMapper.getGoodsByShopId(shopId);
        if (goodsList == null) {
            throw new SelectException("11");
        }
        return goodsApi.getGoodsVOList(goodsList);
    }

    /**
     * @param applyShopDTO
     * @return 商店Id shopId
     * @throws SelectException <br/>
     *                         10:未找到user<br/>
     *                         11:商店名已经存在<br/>
     * @throws AddException    <br/>
     *                         12:添加商店失败<br/>
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Long addShop(ApplyShopDTO applyShopDTO) throws SelectException, AddException {
        //查找用户id
        User user = userMapper.selectByPrimaryKey(applyShopDTO.getUserId());
        if (user == null) {
            throw new SelectException("10");
        }
        //查找shop name是否重名
        if (shopMapper.getShopByName(applyShopDTO.getName()) != null) {
            throw new SelectException("11");
        }
        Shop shop = mapper.map(applyShopDTO, Shop.class);
        shop.setUserId(applyShopDTO.getUserId());
        shop.setMoney(0D);
        shop.setState(0);
        //增加商店
        if (shopMapper.insertSelective(shop) != 1) {
            throw new AddException("12");
        }
        shop = shopMapper.getShopByName(applyShopDTO.getName());
        return shop.getShopId();
    }

    /**
     * @param addGoodsDTO
     * @throws SelectException <br/>
     *                         10:未找到shop<br/>
     *                         11:自家商品名已经存在<br/>
     *                         13:该店铺还在审核<br/>
     * @throws AddException    <br/>
     *                         12:添加商品失败<br/>
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addGoods(AddGoodsDTO addGoodsDTO) throws SelectException, AddException {
        //查找商店
        Shop shop = shopMapper.selectByPrimaryKey(addGoodsDTO.getShopId());
        if (shop == null) {
            throw new SelectException("10");
        }
        if (shop.getState() == 0) {
            throw new SelectException("13");
        }
        //查找自家商品名是否重名
        if (goodsMapper.selectByGoodsIdAndShopId(addGoodsDTO.getGoodsId(), addGoodsDTO.getShopId()) != null) {
            throw new SelectException("11");
        }
        if (addGoodsDTO.getDiscount() == null || addGoodsDTO.getDiscount() < 0 || addGoodsDTO.getDiscount() > 10) {
            addGoodsDTO.setDiscount(10D);
        }
        if (Integer.valueOf(addGoodsDTO.getType()) < 0) {
            addGoodsDTO.setType(0);
        }
        Goods goods = mapper.map(addGoodsDTO, Goods.class);
        //增加商品
        if (goodsMapper.insertSelective(goods) != 1) {
            throw new AddException("12");
        }
        Warehouse warehouse = new Warehouse();
        warehouse.setShopId(shop.getShopId());
        warehouse.setGoodsId(goods.getGoodsId());
        warehouse.setNumber(1);
        if (warehouseMapper.insertSelective(warehouse) != 1) {
            throw new AddException("12");
        }
    }

    /**
     * @param receivingAddressDTO
     * @throws SelectException <br/>
     *                         10:未找到user<br/>
     * @throws AddException    <br/>
     *                         12:添加收货地址失败<br/>
     */
    @Override
    public void addReceivingAddress(ReceivingAddressDTO receivingAddressDTO) throws SelectException, AddException {
        //查找用户id
        User user = userMapper.selectByPrimaryKey(receivingAddressDTO.getUserId());
        if (user == null) {
            throw new SelectException("10");
        }
        ReceivingAddress receivingAddress = mapper.map(receivingAddressDTO, ReceivingAddress.class);
        //增加收货地址
        if (receivingAddressMapper.insertSelective(receivingAddress) != 1) {
            throw new AddException("12");
        }
    }

    @Override
    public boolean deleteShop(Long shopId) {
        return shopMapper.deleteByPrimaryKey(shopId) > 0 ? true : false;
    }

    @Override
    public boolean deleteGoods(Long goodsId) {
        return goodsMapper.deleteByPrimaryKey(goodsId) > 0 ? true : false;
    }

    @Override
    public boolean deleteReceivingAddress(Long receivingAddressId) {
        return receivingAddressMapper.deleteByPrimaryKey(receivingAddressId) > 0 ? true : false;
    }

    /**
     * @param cartGoodsDTOList
     * @return
     * @throws TransactionException <br/>
     *                              1:参数数据和数据库比对不正确
     */
    @Override
    public double compareToDBAndGetAll(List<CartGoodsDTO> cartGoodsDTOList) throws TransactionException {
        double all = 0;
        for (CartGoodsDTO cg : cartGoodsDTOList) {
            boolean flag = false;
            Goods goods = goodsMapper.selectByPrimaryKey(cg.getGoodsId());
            if (goods != null) {
                flag = cg.getGoodsId() != goods.getGoodsId() ? true :
                        cg.getPrice() != goods.getPrice() ? true : false;
            }
            if (flag) {
                throw new TransactionException("1");
            }
            all += goods.getDiscount() * goods.getPrice();
        }
        return all;
    }

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
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Map getOrder(Long userId, CartVO cartVO) throws DeleteException, SelectException, AddException {
        //返回结果
//        TransactionUserAndShopDTO transactionUserAndShopDTO = new TransactionUserAndShopDTO();
//        List<ShopOrderGetDTO> shopOrderGetDTOList = new ArrayList<>();
        Map map = new HashMap();
        List<Long> orderIdList = new ArrayList<>();
        Double all = 0D;
        User user;
        if (userId == null || (user = userMapper.selectByPrimaryKey(userId)) == null) {
            throw new SelectException("10");
        }
        UserLoginDTO userLoginDTO = mapper.map(user, UserLoginDTO.class);
        for (CartGoodsVO cartGoodsVO : cartVO.getCartGoodsVOList()) {
            //每个商品对应的需要修改商铺的信息,userId; goodsId; number; money;
//            ShopOrderGetDTO shopOrderGetDTO = new ShopOrderGetDTO();
//            shopOrderGetDTO.setUserId(userId);
//            shopOrderGetDTO.setGoodsId(cartGoodsVO.getGoodsDTO().getGoodsId());
//            shopOrderGetDTO.setNumber(cartGoodsVO.getNumber());
//            shopOrderGetDTO.setMoney(cartGoodsVO.getNumber()*cartGoodsVO.getGoodsDTO().getPrice());

            all += cartGoodsVO.getGoodsDTO().getPrice() * cartGoodsVO.getGoodsDTO().getDiscount() * cartGoodsVO.getNumber();
            Goods goods;
            if ((goods = goodsMapper.selectByPrimaryKey(cartGoodsVO.getGoodsDTO().getGoodsId())) == null) {
                throw new SelectException("13");
            }
            Shop shop;
            if ((shop = shopMapper.selectByPrimaryKey(goodsMapper.selectByPrimaryKey(cartGoodsVO.getGoodsDTO().getGoodsId()).getShopId())) == null) {
                throw new SelectException("14");
            }
            Warehouse warehouse;
            if ((warehouse = warehouseMapper.getWarehouseByShopIdAndGoodsId(shop.getShopId(), cartGoodsVO.getGoodsDTO().getGoodsId())) == null) {
                if (warehouse.getNumber() < 0) {
                    throw new SelectException("16");
                }
                if (warehouse.getNumber() - cartGoodsVO.getNumber() < 0) {
                    throw new SelectException(shop.getName() + "的库存不够");
                }
            }
            Date nowTime = new Date(System.currentTimeMillis());
            Order order = new Order(null, userId, shop.getShopId(), cartGoodsVO.getGoodsDTO().getGoodsId()
                    , cartGoodsVO.getNumber(), goods.getPrice() * goods.getDiscount(), 1
                    , nowTime, 0, null, null, 0, 0, null, -1);

            Long orderId = orderMapper.getOrderIdByStateAndUserIdAndGoodsId(-1, userId, cartGoodsVO.getGoodsDTO().getGoodsId());
            if (orderId == null) {
                if (orderMapper.insert(order) != 1) {
                    throw new AddException("17");
                }
                orderId = order.getOrderId();
            }
            orderIdList.add(orderId);
            /*if (cartGoodsMapper.deleteByPrimaryKey(cartGoodsVO.getCartGoodsId()) != 1) {
                throw new DeleteException("18");
            }*/
            if (cartGoodsMapper.updateType(cartGoodsVO.getCartGoodsId(), 1) != 1) {
                throw new DeleteException("18");
            }
//            orderIdList.add(orderMapper.getOrderIdByTimeAndUserId(nowTime, userId));
//            transactionUserAndShopDTO.setShopOrderGetDTOList(shopOrderGetDTOList);
        }
//        transactionUserAndShopDTO.setAll(all);
//        transactionUserAndShopDTO.setUserPaymentDTO(userLoginDTO);
        map.put("all", all);
        map.put("orderIdList", orderIdList);
        return map;
    }

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
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean pay(Double all, Long userId, String paymentPassword, List<Long> orderIdList, List<Long> cartGoodsIdList) throws DeleteException, SelectException, UpdateException {
        //user扣钱
        User user;
        if (userId == null || (user = userMapper.selectByPrimaryKey(userId)) == null) {
            throw new SelectException("10");
        }
        double resultMoney = user.getMoney() - all;
        if (resultMoney < 0) {
            throw new SelectException("11");
        }
        user.setMoney(resultMoney);
        if (userMapper.updateByPrimaryKeySelective(user) != 1) {
            throw new UpdateException("12");
        }
        //商家增加钱以及修改订单等
        for (Long orderId : orderIdList) {
            Order order = orderMapper.selectByPrimaryKey(orderId);
            if (order == null) {
                throw new SelectException("13");
            }
            Shop shop = shopMapper.selectByPrimaryKey(order.getShopId());
            if (shop == null) {
                throw new SelectException("14");
            }
            Warehouse warehouse = warehouseMapper.getWarehouseByShopIdAndGoodsId(order.getShopId(), order.getGoodsId());
            if (warehouse == null) {
                throw new SelectException("15");
            }
            shop.setMoney(shop.getMoney() + order.getPrice() * order.getAmount());
            warehouse.setNumber(warehouse.getNumber() - order.getAmount());
            if (warehouse.getNumber() < 0) {
                throw new UpdateException("16");
            }
            if (shopMapper.updateByPrimaryKeySelective(shop) != 1) {
                throw new UpdateException("17");
            }
            if (warehouseMapper.updateByPrimaryKeySelective(warehouse) != 1) {
                throw new UpdateException("18");
            }
            order.setState(0);
            if (orderMapper.updateByPrimaryKeySelective(order) != 1) {
                throw new UpdateException("19");
            }
        }
        for (Long cartGoodsId : cartGoodsIdList) {
            if (cartGoodsMapper.deleteByPrimaryKey(cartGoodsId) != 1) {
                throw new DeleteException("20");
            }
        }
        return true;
    }

    /**
     * @param orderId
     * @return purchasedId
     * @throws UpdateException <br/>
     *                         10:order(订单)更新失败
     * @throws AddException    <br/>
     *                         11:purchased(已购买)添加失败
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Long confirmGoods(Long orderId) throws UpdateException, AddException {
        if (orderMapper.updateByPrimaryKeySelective(new Order(orderId,1,new Date(System.currentTimeMillis()))) != 1) {
            throw new UpdateException("10");
        }
        Purchased purchased = new Purchased(null,orderId,new Date(System.currentTimeMillis()),0,null,null,0);
        if (purchasedMapper.insertSelective(purchased) != 1) {
            throw new AddException("11");
        }
        return purchased.getPurchasedId();
    }

    @Override
    public int sendGoods(Long orderId) {
        return orderMapper.updateSendGoods(orderId);
    }


}