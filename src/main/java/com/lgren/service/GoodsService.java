package com.lgren.service;

import com.github.pagehelper.PageInfo;
import com.lgren.pojo.po.Goods;
import com.lgren.pojo.vo.GoodsVO;

import java.util.List;

public interface GoodsService {
    int deleteByShopId(Long shopId);

    Goods selectByGoodsIdAndShopId(Long goodsId, Long userId);

    List<Goods> getGoodsByShopId(Long shopId);

    List<Goods> selectAll();

    PageInfo<GoodsVO> selectAllPageInfo(Integer pageNum, Integer pageSize);

    PageInfo<GoodsVO> selectFindInfo(String content,Integer pageNum, Integer pageSize);

    int deleteByPrimaryKey(Long goodsId);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Long goodsId);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);
}