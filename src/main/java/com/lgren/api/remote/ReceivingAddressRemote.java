package com.lgren.api.remote;

import com.lgren.pojo.po.ReceivingAddress;
import com.lgren.service.ReceivingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/remote")
public class ReceivingAddressRemote {
    @Autowired
    private ReceivingAddressService receivingAddressService;
    //查询所有
    @RequestMapping(value = "/receivingAddress.do",method = RequestMethod.GET)
    public List<ReceivingAddress> getAllReceivingAddress() {
        return receivingAddressService.selectAll();
    }
    //根据主键ID查询
    @RequestMapping(value = "/receivingAddress.do/{receivingAddressId}",method = RequestMethod.GET)
    public ReceivingAddress getReceivingAddressById(@PathVariable("receivingAddressId") Long receivingAddressId) {
        return receivingAddressService.selectByPrimaryKey(receivingAddressId);
    }
    //添加
    @RequestMapping(value = "/receivingAddress.do",method = RequestMethod.POST)
    public boolean addReceivingAddress(ReceivingAddress receivingAddress) {
        return receivingAddressService.insertSelective(receivingAddress) > 0 ? true : false;
    }
    //通过主键ID删除
    @RequestMapping(value = "/receivingAddress.do/{receivingAddressId}",method = RequestMethod.DELETE)
    public boolean deleteReceivingAddressById(@PathVariable("receivingAddressId") Long receivingAddressId) {
        return receivingAddressService.deleteByPrimaryKey(receivingAddressId) > 0 ? true : false;
    }
    //通过传输一个ReceivingAddress对象修改
    @RequestMapping(value = "/receivingAddress.do",method = RequestMethod.PUT)
    public boolean updateReceivingAddressById(ReceivingAddress receivingAddress) {
        return receivingAddressService.updateByPrimaryKeySelective(receivingAddress) > 0 ? true : false;
    }



}
