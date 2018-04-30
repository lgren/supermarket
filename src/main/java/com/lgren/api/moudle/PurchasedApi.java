package com.lgren.api.moudle;

import com.lgren.pojo.dto.PurchasedDTO;
import com.lgren.pojo.po.Order;
import com.lgren.pojo.po.Purchased;
import com.lgren.pojo.vo.OrderVO;
import com.lgren.pojo.vo.PurchasedVO;
import com.lgren.service.OrderService;
import com.lgren.service.PurchasedService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PurchasedApi {//OK
    @Autowired
    private Mapper mapper;
    @Autowired
    private PurchasedService purchasedService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderApi orderApi;
    //查询所有
    public List<PurchasedVO> getAllPurchased() {
        List<Purchased> purchasedList = purchasedService.selectAll();
        List<PurchasedVO> purchasedVOList = getPurchasedVOList(purchasedList);
        return purchasedVOList;
    }
    //根据主键ID查询
    public PurchasedVO getPurchasedById(Long purchasedId) {
        Purchased purchased = purchasedService.selectByPrimaryKey(purchasedId);
        return getPurchasedVO(purchased);
    }
    //根据userID查询
    public List<PurchasedVO> getPurchasedVOListByUserId(Long userId) {
        List<Order> orderList = orderService.getOrderListByUserId(userId);
        return orderList.stream()
                .map(order -> purchasedService.selectByOrderId(order.getOrderId()))
                .filter(purchased -> purchased != null)
                .map(purchased -> getPurchasedVO(purchased))
                .collect(Collectors.toList());
    }
    //根据userID查询
    public List<PurchasedVO> getPurchasedVOListByUserIdAndEvaluation(Long userId,Integer evaluation) {
        List<Order> orderList = orderService.getOrderListByUserId(userId);
        List<PurchasedVO> purchasedVOList = new ArrayList<>();
        return orderList.stream()
                .map(order -> purchasedService.selectByOrderIdAndEvaluation(order.getOrderId(),evaluation))
                .filter(purchased -> purchased != null)
                .map(purchased -> getPurchasedVO(purchased))
                .collect(Collectors.toList());
    }
    //添加
    public boolean addPurchased(PurchasedDTO purchasedDTO) {
        return purchasedService.insertSelective(mapper.map(purchasedDTO,Purchased.class)) > 0 ? true : false;
    }
    //通过主键ID删除
    public boolean deletePurchasedById(Long purchasedId) {
        return purchasedService.deleteByPrimaryKey(purchasedId) > 0 ? true : false;
    }
    //通过传输一个Purchased对象修改
    public boolean updatePurchasedById(PurchasedDTO purchasedDTO) {
        return purchasedService.updateByPrimaryKeySelective(mapper.map(purchasedDTO,Purchased.class)) > 0 ? true : false;
    }

    private List<PurchasedVO> getPurchasedVOList(List<Purchased> purchasedList) {
        List<PurchasedVO> purchasedVOList = purchasedList.stream()
                .map(cg -> getPurchasedVO(cg))
                .collect(Collectors.toList());
        return purchasedVOList;
    }

    private PurchasedVO getPurchasedVO(Purchased purchased) {
        PurchasedVO purchasedVO = mapper.map(purchased,PurchasedVO.class);
        OrderVO orderVO = orderApi.getOrderById(purchased.getOrderId());
        purchasedVO.setOrderVO(orderVO);
        return purchasedVO;
    }
}

