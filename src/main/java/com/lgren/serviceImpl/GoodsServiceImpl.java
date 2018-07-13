package com.lgren.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lgren.dao.CartGoodsMapper;
import com.lgren.dao.CollectGoodsMapper;
import com.lgren.dao.GoodsMapper;
import com.lgren.dao.OrderMapper;
import com.lgren.pojo.dto.ShopDTO;
import com.lgren.pojo.po.Goods;
import com.lgren.pojo.po.Shop;
import com.lgren.pojo.vo.GoodsVO;
import com.lgren.service.GoodsService;
import com.lgren.service.ShopService;
import com.lgren.service.WarehouseService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private Mapper mapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CollectGoodsMapper collectGoodsMapper;
    @Autowired
    private ShopService shopService;
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private CartGoodsMapper cartGoodsService;


    @Override
    public int deleteByShopId(Long shopId) {
        List<Goods> goodsList = getGoodsByShopId(shopId);
        if (goodsList.size() == 0) {
            return 0;
        }
        goodsList.stream().map(g -> g.getGoodsId()).forEach(g -> {
            cartGoodsService.deleteByGoodsId(g);
            collectGoodsMapper.deleteByGoodsId(g);
        });
        orderMapper.deleteByShopId(shopId);
        goodsMapper.deleteByShopId(shopId);
        return 1;
    }

    @Override
    public Goods selectByGoodsIdAndShopId(Long goodsId, Long userId) {
        return goodsMapper.selectByGoodsIdAndShopId(goodsId,userId);
    }

    @Override
    public List<Goods> getGoodsByShopId(Long shopId) {
        return goodsMapper.getGoodsByShopId(shopId);
    }

    @Override
    public List<Goods> selectAll() {
        return goodsMapper.selectAll();
    }

    @Override
    public PageInfo<GoodsVO> selectAllPageInfo(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Goods> goodsList = goodsMapper.selectAll();
        PageInfo<Goods> goodsPageInfo = new PageInfo<>(goodsList);
        List<GoodsVO> goodsVOList = goodsList.stream().map(goods -> getGoodsVO(goods)).collect(Collectors.toList());
        PageInfo goodsVOPageInfo = goodsPageInfo;
        goodsVOPageInfo.setList(goodsVOList);
        return goodsVOPageInfo;
    }

    @Override
    public PageInfo<GoodsVO> selectFindInfo(String content, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Goods> goodsList = goodsMapper.selectFind(content);
        PageInfo<Goods> goodsPageInfo = new PageInfo<>(goodsList);
        List<GoodsVO> goodsVOList = goodsList.stream().map(goods -> getGoodsVO(goods)).collect(Collectors.toList());
        PageInfo goodsVOPageInfo = goodsPageInfo;
        goodsVOPageInfo.setList(goodsVOList);
        return goodsVOPageInfo;
    }

    @Override
    public int deleteByPrimaryKey(Long goodsId) {
        return goodsMapper.deleteByPrimaryKey(goodsId);
    }

    @Override
    public int insert(Goods record) {
        return goodsMapper.insert(record);
    }

    @Override
    public int insertSelective(Goods record) {
        return goodsMapper.insertSelective(record);
    }

    @Override
    public Goods selectByPrimaryKey(Long goodsId) {
        return goodsMapper.selectByPrimaryKey(goodsId);
    }

    @Override
    public int updateByPrimaryKeySelective(Goods record) {
        return goodsMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Goods record) {
        return goodsMapper.updateByPrimaryKey(record);
    }

    public GoodsVO getGoodsVO(Goods goods) {
        if (goods == null) {
            return null;
        }
        Shop shop = shopService.selectByPrimaryKey(goods.getShopId());
        GoodsVO goodsVO = mapper.map(goods,GoodsVO.class);
        if (shop != null) {
            goodsVO.setShopDTO(mapper.map(shop,ShopDTO.class));
        }
        goodsVO.setDiscount(goodsVO.getDiscount()*10);
        goodsVO.setNumber(warehouseService.getWarehouseByShopIdAndGoodsId(shop.getShopId(),goods.getGoodsId()).getNumber());
        return goodsVO;
    }
}