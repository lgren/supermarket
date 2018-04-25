package com.lgren.api.remote;

import com.lgren.pojo.po.Refund;
import com.lgren.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/remote")
public class RefundRemote {
    @Autowired
    private RefundService refundService;
    //查询所有
    @RequestMapping(value = "/refund.do",method = RequestMethod.GET)
    public List<Refund> getAllRefund() {
        return refundService.selectAll();
    }
    //根据主键ID查询
    @RequestMapping(value = "/refund.do/{refundId}",method = RequestMethod.GET)
    public Refund getRefundById(@PathVariable("refundId") Long refundId) {
        return refundService.selectByPrimaryKey(refundId);
    }
    //添加
    @RequestMapping(value = "/refund.do",method = RequestMethod.POST)
    public boolean addRefund(Refund refund) {
        return refundService.insertSelective(refund) > 0 ? true : false;
    }
    //通过主键ID删除
    @RequestMapping(value = "/refund.do/{refundId}",method = RequestMethod.DELETE)
    public boolean deleteRefundById(@PathVariable("refundId") Long refundId) {
        return refundService.deleteByPrimaryKey(refundId) > 0 ? true : false;
    }
    //通过传输一个Refund对象修改
    @RequestMapping(value = "/refund.do",method = RequestMethod.PUT)
    public boolean updateRefundById(Refund refund) {
        return refundService.updateByPrimaryKeySelective(refund) > 0 ? true : false;
    }



}
