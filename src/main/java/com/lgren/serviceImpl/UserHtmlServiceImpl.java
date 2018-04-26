package com.lgren.serviceImpl;

import com.lgren.api.moudle.GoodsApi;
import com.lgren.api.moudle.ShopApi;
import com.lgren.controller.user.dto.ApplyShopDTO;
import com.lgren.controller.user.dto.MyShopDTO;
import com.lgren.controller.user.dto.UserRegistrationDTO;
import com.lgren.dao.*;
import com.lgren.exception.AddException;
import com.lgren.exception.SelectException;
import com.lgren.exception.UpdateException;
import com.lgren.pojo.dto.GoodsDTO;
import com.lgren.pojo.dto.ReceivingAddressDTO;
import com.lgren.pojo.po.*;
import com.lgren.pojo.vo.GoodsVO;
import com.lgren.pojo.vo.ShopVO;
import com.lgren.service.UserHtmlService;
import com.lgren.service.WarehouseService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    private WarehouseService warehouseService;
    @Autowired
    private GoodsApi goodsApi;
    @Autowired
    private ShopApi shopApi;

    /**
     * @param userLoginDTO
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
     * @param goodsDTO
     * @return
     * @throws SelectException <br/>
     *                         10:未找到goods<br/>
     *                         12:未找到商店<br/>
     *                         13:该店铺还在审核<br/>
     * @throws UpdateException <br/>
     *                         11:修改失败<br/>
     */
    @Override
    public void goodsUpdate(GoodsDTO goodsDTO) throws SelectException, UpdateException {
        //查找商店
        Shop shop = shopMapper.selectByPrimaryKey(goodsDTO.getShopId());
        if (shop == null) {
            throw new SelectException("12");
        }
        if (shop.getState() == 0) {
            throw new SelectException("13");
        }

        Goods goods = goodsMapper.selectByGoodsIdAndShopId(goodsDTO.getGoodsId(), goodsDTO.getShopId());
        if (goods == null) {
            throw new SelectException("10");
        }
        if (goodsDTO.getDiscount() == null || goodsDTO.getDiscount() > 10 || goodsDTO.getDiscount() < 0) {
            goodsDTO.setDiscount(10D);
        }
        if (Integer.valueOf(goodsDTO.getType()) < 0) {
            goodsDTO.setType(0);
        }
        goods = mapper.map(goodsDTO, Goods.class);
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
     * @param goodsDTO
     * @throws SelectException <br/>
     *                         10:未找到shop<br/>
     *                         11:自家商品名已经存在<br/>
     *                         13:该店铺还在审核<br/>
     * @throws AddException    <br/>
     *                         12:添加商品失败<br/>
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addGoods(GoodsDTO goodsDTO) throws SelectException, AddException {
        //查找商店
        Shop shop = shopMapper.selectByPrimaryKey(goodsDTO.getShopId());
        if (shop == null) {
            throw new SelectException("10");
        }
        if (shop.getState() == 0) {
            throw new SelectException("13");
        }
        //查找自家商品名是否重名
        if (goodsMapper.selectByGoodsIdAndShopId(goodsDTO.getGoodsId(), goodsDTO.getShopId()) != null) {
            throw new SelectException("11");
        }
        if (goodsDTO.getDiscount() == null || goodsDTO.getDiscount() < 0 || goodsDTO.getDiscount() > 10) {
            goodsDTO.setDiscount(10D);
        }
        if (Integer.valueOf(goodsDTO.getType()) < 0) {
            goodsDTO.setType(0);
        }
        Goods goods = mapper.map(goodsDTO, Goods.class);
        //增加商店
        if (goodsMapper.insertSelective(goods) != 1) {
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


}