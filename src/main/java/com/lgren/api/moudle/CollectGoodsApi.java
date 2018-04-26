package com.lgren.api.moudle;

import com.lgren.pojo.dto.CollectDTO;
import com.lgren.pojo.dto.CollectGoodsDTO;
import com.lgren.pojo.dto.GoodsDTO;
import com.lgren.pojo.po.Collect;
import com.lgren.pojo.po.CollectGoods;
import com.lgren.pojo.po.Goods;
import com.lgren.pojo.vo.CollectGoodsVO;
import com.lgren.service.CollectGoodsService;
import com.lgren.service.CollectService;
import com.lgren.service.GoodsService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CollectGoodsApi {//OK
    @Autowired
    private Mapper mapper;
    @Autowired
    private CollectGoodsService collectGoodsService;
    @Autowired
    private CollectService collectService;
    @Autowired
    private GoodsService goodsService;
    //查询所有
    public List<CollectGoodsVO> getAllCollectGoods() {
        List<CollectGoods> collectGoodsList = collectGoodsService.selectAll();
        List<CollectGoodsVO> collectGoodsVOList = getCollectGoodsVOList(collectGoodsList);
        return collectGoodsVOList;
    }
    public List<CollectGoodsVO> getCollectGoodsByCollectId(Long collectId) {
        List<CollectGoods> collectGoodsList = collectGoodsService.getCollectGoodsByCollectId(collectId);
        List<CollectGoodsVO> collectGoodsVOList = getCollectGoodsVOList(collectGoodsList);
        return collectGoodsVOList;
    }
    public List<CollectGoodsVO> getCollectGoodsByGoodsId(Long goodsId) {
        List<CollectGoods> collectGoodsList = collectGoodsService.getCollectGoodsByGoodsId(goodsId);
        List<CollectGoodsVO> collectGoodsVOList = getCollectGoodsVOList(collectGoodsList);
        return collectGoodsVOList;
    }
    //根据主键ID查询
    public CollectGoodsVO getCollectGoodsById(Long collectGoodsId) {
        CollectGoods collectGoods = collectGoodsService.selectByPrimaryKey(collectGoodsId);
        CollectGoodsVO collectGoodsVO = mapper.map(collectGoods,CollectGoodsVO.class);

        Collect collect = collectService.selectByPrimaryKey(collectGoods.getCollectId());
        Goods goods = goodsService.selectByPrimaryKey(collectGoods.getGoodsId());
        collectGoodsVO.setCollectDTO(mapper.map(collect,CollectDTO.class));
        collectGoodsVO.setGoodsDTO(mapper.map(goods,GoodsDTO.class));


        return collectGoodsVO;
    }
    //添加
    public boolean addCollectGoods(CollectGoodsDTO collectGoodsDTO) {
        return collectGoodsService.insertSelective(mapper.map(collectGoodsDTO,CollectGoods.class)) > 0 ? true : false;
    }
    //通过主键ID删除
    public boolean deleteCollectGoodsById(Long collectGoodsId) {
        return collectGoodsService.deleteByPrimaryKey(collectGoodsId) > 0 ? true : false;
    }
    //通过传输一个CollectGoods对象修改
    public boolean updateCollectGoodsById(CollectGoodsDTO collectGoodsDTO) {
        return collectGoodsService.updateByPrimaryKeySelective(mapper.map(collectGoodsDTO,CollectGoods.class)) > 0 ? true : false;
    }



    private List<CollectGoodsVO> getCollectGoodsVOList(List<CollectGoods> collectGoodsList) {
        List<CollectGoodsVO> collectGoodsVOList = collectGoodsList.stream()
                .map(cg -> getCollectGoodsVO(cg))
                .collect(Collectors.toList());
        return collectGoodsVOList;
    }
    private CollectGoodsVO getCollectGoodsVO(CollectGoods collectGoods) {
        CollectGoodsVO collectGoodsVO = mapper.map(collectGoods,CollectGoodsVO.class);
        Collect collect = collectService.selectByPrimaryKey(collectGoods.getCollectId());
        Goods goods = goodsService.selectByPrimaryKey(collectGoods.getGoodsId());
        collectGoodsVO.setCollectDTO(mapper.map(collect,CollectDTO.class));
        collectGoodsVO.setGoodsDTO(mapper.map(goods,GoodsDTO.class));
        return collectGoodsVO;
    }
}
