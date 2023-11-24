package com.ouc.malladmin.service.impl;

import com.ouc.malladmin.service.OrderService;
import com.ouc.mallmbg.mapper.OrderMapper;
import com.ouc.mallmbg.model.Order;
import com.ouc.mallmbg.model.OrderExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Override
    public List<Order> getorders(OrderExample orderExample){
        List<Order> orders = new ArrayList<Order>();
        orders = orderMapper.selectByExample(orderExample);
        return orders;
    }
    @Override
    public Order getorder(Integer id){
        Order order = new Order();
        order = orderMapper.selectByPrimaryKey(id);
        return order;
    }
    @Override
    public  void deleteorder(Integer id){
        orderMapper.deleteByPrimaryKey(id);
    }
    @Override
    public void updateorder(Order order){
        order.setReceiverName(order.getReceiverName());
        order.setCommitTime(order.getCommitTime());
        order.setUserId(order.getUserId());
        order.setProductId(order.getProductId());
        order.setTotalPrice(order.getTotalPrice());
        order.setAmount(order.getAmount());
        order.setStatus(order.getStatus());
        order.setAddress(order.getAddress());
        order.setPhoneNumber(order.getPhoneNumber());
        order.setConfiguration(order.getConfiguration());
        orderMapper.updateByPrimaryKeySelective(order);
    }
}
