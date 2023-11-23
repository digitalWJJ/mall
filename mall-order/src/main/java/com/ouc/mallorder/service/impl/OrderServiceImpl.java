package com.ouc.mallorder.service.impl;

import com.ouc.mallmbg.mapper.OrderMapper;
import com.ouc.mallmbg.model.Order;
import com.ouc.mallmbg.model.OrderExample;
import com.ouc.mallorder.service.OrderService;
import jakarta.annotation.Resource;

import java.util.List;


public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderMapper orderMapper;

    @Override
    public List<Order> getMyCart(int id) {
        OrderExample orderExample=new OrderExample();
        OrderExample.Criteria criteria=orderExample.createCriteria();
        criteria.andUserIdEqualTo(id);
        return orderMapper.selectByExample(orderExample);
    }

    @Override
    public boolean deleteProduct(int id) {
      return orderMapper.deleteByPrimaryKey(id)>0?true:false;
    }

    @Override
    public boolean changeProductAmount(int id,int amount) {
        Order order=orderMapper.selectByPrimaryKey(id);
        if(order==null)return false;
        order.setAmount(amount);
        return orderMapper.updateByPrimaryKey(order)>0?true:false;
    }

    @Override
    public List<Order> confirmBuyOrder(int userId) {
//        OrderExample orderExample=new OrderExample();
//        OrderExample.Criteria criteria=OrderMapper.
        return null;
    }
}
