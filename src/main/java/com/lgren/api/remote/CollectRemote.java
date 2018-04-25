package com.lgren.api.remote;

import com.lgren.pojo.po.Collect;
import com.lgren.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/remote")
public class CollectRemote {
    @Autowired
    private CollectService collectService;
    //查询所有
    @RequestMapping(value = "/collect.do",method = RequestMethod.GET)
    public List<Collect> getAllCollect() {
        return collectService.selectAll();
    }
    //根据主键ID查询
    @RequestMapping(value = "/collect.do/{collectId}",method = RequestMethod.GET)
    public Collect getCollectById(@PathVariable("collectId") Long collectId) {
        return collectService.selectByPrimaryKey(collectId);
    }
    //添加
    @RequestMapping(value = "/collect.do",method = RequestMethod.POST)
    public boolean addCollect(Collect collect) {
        return collectService.insertSelective(collect) > 0 ? true : false;
    }
    //通过主键ID删除
    @RequestMapping(value = "/collect.do/{collectId}",method = RequestMethod.DELETE)
    public boolean deleteCollectById(@PathVariable("collectId") Long collectId) {
        return collectService.deleteByPrimaryKey(collectId) > 0 ? true : false;
    }
    //通过传输一个Collect对象修改
    @RequestMapping(value = "/collect.do",method = RequestMethod.PUT)
    public boolean updateCollectById(Collect collect) {
        return collectService.updateByPrimaryKeySelective(collect) > 0 ? true : false;
    }



}
