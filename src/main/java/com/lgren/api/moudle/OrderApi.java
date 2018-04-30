package com.lgren.api.moudle;

import com.lgren.pojo.dto.GoodsDTO;
import com.lgren.pojo.dto.OrderDTO;
import com.lgren.pojo.dto.ShopDTO;
import com.lgren.pojo.dto.UserDTO;
import com.lgren.pojo.po.Order;
import com.lgren.pojo.vo.OrderVO;
import com.lgren.service.GoodsService;
import com.lgren.service.OrderService;
import com.lgren.service.ShopService;
import com.lgren.service.UserService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OrderApi {//OK
    @Autowired
    private Mapper mapper;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private GoodsService goodsService;
    //查询所有
    public List<OrderVO> getAllOrder() {
        List<Order> orderList = orderService.selectAll();
        List<OrderVO> orderVOList = getOrderVOList(orderList);
        return orderVOList;
    }
    public List<OrderVO> getOrderByUserId(Long userId) {
        List<Order> orderList = orderService.getOrderListByUserId(userId);
        List<OrderVO> orderVOList = getOrderVOList(orderList);
        return orderVOList;
    }
    public List<OrderVO> getOrderByShopId(Long shopId) {
        List<Order> orderList = orderService.getOrderListByShopId(shopId);
        List<OrderVO> orderVOList = getOrderVOList(orderList);
        return orderVOList;
    }
    public List<OrderVO> getOrderByGoodsId(Long goodsId) {
        List<Order> orderList = orderService.getOrderListByGoodsId(goodsId);
        List<OrderVO> orderVOList = getOrderVOList(orderList);
        return orderVOList;
    }
    //根据主键ID查询
    public OrderVO getOrderById(Long orderId) {
        Order order = orderService.selectByPrimaryKey(orderId);
        return getOrderVO(order);
    }
    //添加
    public boolean addOrder(OrderDTO orderDTO) {
        return orderService.insertSelective(mapper.map(orderDTO,Order.class)) > 0 ? true : false;
    }
    //通过主键ID删除
    public boolean deleteOrderById(Long orderId) {
        return orderService.deleteByPrimaryKey(orderId) > 0 ? true : false;
    }
    //通过传输一个Order对象修改
    public boolean updateOrderById(OrderDTO orderDTO) {
        return orderService.updateByPrimaryKeySelective(mapper.map(orderDTO,Order.class)) > 0 ? true : false;
    }

    private List<OrderVO> getOrderVOList(List<Order> orderList) {
        List<OrderVO> orderVOList = orderList.stream()
                .map(cg -> getOrderVO(cg))
                .collect(Collectors.toList());
        return orderVOList;
    }

    private OrderVO getOrderVO(Order order) {
        OrderVO orderVO = mapper.map(order,OrderVO.class);
        UserDTO userDTO = mapper.map(userService.selectByPrimaryKey(order.getUserId()),UserDTO.class);
        ShopDTO shopDTO = mapper.map(shopService.selectByPrimaryKey(order.getShopId()),ShopDTO.class);
        GoodsDTO goodsDTO = mapper.map(goodsService.selectByPrimaryKey(order.getGoodsId()),GoodsDTO.class);
        orderVO.setUserDTO(userDTO);
        orderVO.setShopDTO(shopDTO);
        orderVO.setGoodsDTO(goodsDTO);
        return orderVO;
    }
}

