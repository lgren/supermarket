package com.lgren.api.moudle;

import com.lgren.pojo.dto.CollectDTO;
import com.lgren.pojo.dto.UserDTO;
import com.lgren.pojo.po.Collect;
import com.lgren.pojo.vo.CollectGoodsVO;
import com.lgren.pojo.vo.CollectVO;
import com.lgren.service.CollectService;
import com.lgren.service.UserService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CollectApi {//OK
    @Autowired
    private Mapper mapper;
    @Autowired
    private CollectService collectService;
    @Autowired
    private CollectGoodsApi collectGoodsApi;
    @Autowired
    private UserService userService;
    //查询所有
    public List<CollectVO> getAllCollect() {
        List<Collect> collectList = collectService.selectAll();
        List<CollectVO> collectVOList = collectList.stream()
                .map(collect -> getCollectVO(collect))
                .collect(Collectors.toList());
        return collectVOList;
    }
    //根据用户主键ID查询
    public CollectVO getCollectByUserId(Long userId) {
        Collect collect = collectService.getCollectByUserId(userId);
        return getCollectVO(collect);
    }
    //根据主键ID查询
    public CollectVO getCollectById(Long collectId) {
        Collect collect = collectService.selectByPrimaryKey(collectId);
        return getCollectVO(collect);
    }
    //添加
    public boolean addCollect(CollectDTO collectDTO) {
        return collectService.insertSelective(mapper.map(collectDTO,Collect.class)) > 0 ? true : false;
    }
    //通过主键ID删除
    public boolean deleteCollectById(Long collectId) {
        return collectService.deleteByPrimaryKey(collectId) > 0 ? true : false;
    }
    //通过传输一个Collect对象修改
    public boolean updateCollectById(CollectDTO collectDTO) {
        return collectService.updateByPrimaryKeySelective(mapper.map(collectDTO,Collect.class)) > 0 ? true : false;
    }


    private CollectVO getCollectVO(Collect collect) {
        CollectVO collectVO = mapper.map(collect,CollectVO.class);
        UserDTO userDTO = mapper.map(userService.selectByPrimaryKey(collect.getUserId()),UserDTO.class);
        List<CollectGoodsVO> collectGoodsVOList = collectGoodsApi.getCollectGoodsByCollectId(collect.getCollectId());
        collectVO.setUserDTO(userDTO);
        collectVO.setCollectGoodsVOList(collectGoodsVOList);
        return collectVO;
    }



}
