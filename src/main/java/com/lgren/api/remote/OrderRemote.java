package com.lgren.api.remote;

import com.lgren.pojo.po.Order;
import com.lgren.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/remote")
public class OrderRemote {
    @Autowired
    private OrderService orderService;
    //查询所有
    @RequestMapping(value = "/order.do",method = RequestMethod.GET)
    public List<Order> getAllOrder() {
        return orderService.selectAll();
    }
    //根据主键ID查询
    @RequestMapping(value = "/order.do/{orderId}",method = RequestMethod.GET)
    public Order getOrderById(@PathVariable("orderId") Long orderId) {
        return orderService.selectByPrimaryKey(orderId);
    }
    //添加
    @RequestMapping(value = "/order.do",method = RequestMethod.POST)
    public boolean addOrder(Order order) {
        return orderService.insertSelective(order) > 0 ? true : false;
    }
    //通过主键ID删除
    @RequestMapping(value = "/order.do/{orderId}",method = RequestMethod.DELETE)
    public boolean deleteOrderById(@PathVariable("orderId") Long orderId) {
        return orderService.deleteByPrimaryKey(orderId) > 0 ? true : false;
    }
    //通过传输一个Order对象修改
    @RequestMapping(value = "/order.do",method = RequestMethod.PUT)
    public boolean updateOrderById(Order order) {
        return orderService.updateByPrimaryKeySelective(order) > 0 ? true : false;
    }



}
