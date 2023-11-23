package com.ouc.mallproduct.service.impl;

import com.ouc.mallmbg.mapper.OrderMapper;
import com.ouc.mallmbg.mapper.ProductMapper;
import com.ouc.mallmbg.model.Order;
import com.ouc.mallmbg.model.Product;
import jakarta.annotation.Resource;
import com.ouc.mallproduct.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public Product getProduct(int id) {
        return productMapper.selectByPrimaryKey(id);
    }

//    @Override
//    public boolean confirmOrder(int id,String color,String configuration,int amount) {
//       int rowsAffected=insertOrder(id, color, configuration, amount);
//       if(rowsAffected>0)return true;
//       return false;
//    }

    @Override
    public boolean addOrder(int productId,String color,String configuration,int amount) {
        int rowsAffected=insertOrder(productId,color, configuration, amount);
        if(rowsAffected>0)return true;
        return false;
    }
    public int insertOrder(int productId,String color,String configuration,int amount)
    {
        Order order=new Order();
        order.setColor(color);
        order.setProductId(productId);
        order.setAmount(amount);
        order.setConfiguration(configuration);
        order.setCommitTime(new Date(System.currentTimeMillis()));
        order.setStatus("待下单");
        return orderMapper.insert(order);
    }
}
