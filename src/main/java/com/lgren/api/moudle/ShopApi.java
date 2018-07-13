package com.lgren.api.moudle;

import com.lgren.pojo.dto.ShopDTO;
import com.lgren.pojo.dto.UserDTO;
import com.lgren.pojo.po.Shop;
import com.lgren.pojo.vo.ShopVO;
import com.lgren.service.ShopService;
import com.lgren.service.UserService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ShopApi {//OK
    @Autowired
    private Mapper mapper;
    @Autowired
    private ShopService shopService;
    @Autowired
    private UserService userService;
    //查询所有DTO
    public List<ShopDTO> getAllShopDTO() {
        List<ShopDTO> shopDTOList = shopService.selectAll()
                .stream()
                .map(s -> mapper.map(s,ShopDTO.class))
                .collect(Collectors.toList());
        return shopDTOList;
    }
    //查询所有VO
    public List<ShopVO> getAllShopVO() {
        List<Shop> shopList = shopService.selectAll();
        List<ShopVO> shopVOList = shopList.stream()
                .map(shop -> getShopVO(shop))
                .collect(Collectors.toList());
        return shopVOList;
    }
    //模糊查询所有VO
    public List<ShopVO> getFindShopVO(String content) {
        List<Shop> shopList = shopService.selectFind(content);
        List<ShopVO> shopVOList = shopList.stream()
                .map(shop -> getShopVO(shop))
                .collect(Collectors.toList());
        return shopVOList;
    }
    //查询根据userId查询ShopVOList
    public List<ShopVO> getShopVOListByUserId(Long userId) {
        List<Shop> shopList = shopService.getShopByUserId(userId);
        List<ShopVO> shopVOList = shopList.stream()
                .map(shop -> getShopVO(shop))
                .collect(Collectors.toList());
        return shopVOList;
    }
    //根据主键ID查询
    public ShopVO getShopById(Long shopId) {
        Shop shop = shopService.selectByPrimaryKey(shopId);
        return getShopVO(shop);
    }
    //添加
    public boolean addShop(Shop shop) {
        return shopService.insertSelective(shop) > 0 ? true : false;
    }
    //通过主键ID删除
    public boolean deleteShopById(Long shopId) {
        return shopService.deleteByPrimaryKey(shopId) > 0 ? true : false;
    }
    //通过传输一个Shop对象修改
    public boolean updateShopById(Shop shop) {
        return shopService.updateByPrimaryKeySelective(shop) > 0 ? true : false;
    }

    private List<ShopVO> getShopVOList(List<Shop> shopList) {
        List<ShopVO> shopVOList = shopService.selectAll()
                .stream()
                .map(shop -> getShopVO(shop))
                .collect(Collectors.toList());
        return shopVOList;
    }
    private ShopVO getShopVO(Shop shop) {
        ShopVO shopVO = mapper.map(shop,ShopVO.class);
        UserDTO userDTO = mapper.map(userService.selectByPrimaryKey(shop.getUserId()),UserDTO.class);
        shopVO.setUserDTO(userDTO);
        return shopVO;
    }

}
