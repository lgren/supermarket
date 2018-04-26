package com.lgren.serviceImpl;

import com.lgren.api.moudle.GoodsApi;
import com.lgren.api.moudle.ShopApi;
import com.lgren.controller.user.dto.MyShopDTO;
import com.lgren.controller.user.dto.UserLoginDTO;
import com.lgren.dao.*;
import com.lgren.exception.AddException;
import com.lgren.exception.SelectException;
import com.lgren.pojo.po.*;
import com.lgren.pojo.vo.GoodsVO;
import com.lgren.pojo.vo.ShopVO;
import com.lgren.service.UserHtmlService;
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
    private GoodsApi goodsApi;
    @Autowired
    private ShopApi shopApi;

    /**
     *
     * @param userLoginDTO
     * @return
     * @throws AddException <br/>
     *          10:用户已存在 <br/>
     *          11:增加用户失败 <br/>
     *          12:未查找刚插入的用户 <br/>
     *          13:个人购物车增加失败 <br/>
     *          14:个人收藏夹添加失败
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Long addUser(UserLoginDTO userLoginDTO) throws AddException {
        //查找用户id
        User user = userMapper.getUserByUsername(userLoginDTO.getUsername());
        if (user != null) {
            throw new AddException("10");
        }
        //增加用户
        if (userMapper.insert(mapper.map(userLoginDTO,User.class)) != 1) {
            throw new AddException("11");
        }
        user = userMapper.getUserByUsername(userLoginDTO.getUsername());
        if (user == null) {
            throw new AddException("12");
        }
        //增加cart和collect
        Cart cart = new Cart();
        Collect collect = new Collect();
        cart.setUserId(user.getUserId());
        collect.setUserId(user.getUserId());
        if (cartMapper.insertSelective(cart) != 1){
            throw new AddException("13");
        }
        if (collectMapper.insertSelective(collect) != 1){
            throw new AddException("14");
        }
        return user.getUserId();
    }

    @Override
    public List<ShopVO> getMyShopByUserId(Long userId) {
        return shopApi.getShopVOListByUserId(userId);
    }

    /**
     *
     * @param myShopDTO
     * @return
     * @throws SelectException <br/>
     *          10:未找到shop<br/>
     *          11:修改失败<br/>
     */
    @Override
    public void shopUpdate(MyShopDTO myShopDTO) throws SelectException{
        Shop shop = shopMapper.selectByPrimaryKey(myShopDTO.getShopId());
        if (shop == null) {
            throw new SelectException("10");
        }
        shop = mapper.map(myShopDTO,Shop.class);
        if (shopMapper.updateByPrimaryKeySelective(shop) < 1) {
            throw new SelectException("11");
        }
    }

    /**
     *
     * @param shopId
     * @return
     * @throws SelectException <br/>
     *          10:找不到商店 <br/>
     *          11:查询商品异常 <br/>
     */
    @Override
    public List<GoodsVO> getGoodsByShopId(Long shopId) throws SelectException{
        if (shopMapper.selectByPrimaryKey(shopId) == null) {
            throw new SelectException("10");
        }
        List<Goods> goodsList = goodsMapper.getGoodsByShopId(shopId);
        if (goodsList == null) {
            throw new SelectException("11");
        }
        return goodsApi.getGoodsVOList(goodsList);
    }























}