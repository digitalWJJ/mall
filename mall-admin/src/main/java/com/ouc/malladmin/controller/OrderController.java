package com.ouc.malladmin.controller;

import com.ouc.malladmin.service.impl.OrderServiceImpl;
import com.ouc.mallcommon.Result;
import com.ouc.mallmbg.mapper.OrderMapper;
import com.ouc.mallmbg.model.Order;
import com.ouc.mallmbg.model.OrderExample;
import com.ouc.mallmbg.model.Product;
import com.ouc.mallmbg.model.ProductExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

public class OrderController {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderServiceImpl orderServiceImpl;
    @GetMapping("/admin/order/vieworders")
    public Result viewproducts(){
        Result result = new Result();
        List<Order> productList = new ArrayList<Order>();
        OrderExample orderExample = new OrderExample();
        productList = orderServiceImpl.getorders(orderExample);
        Result.success(productList);
        return result;
    }
    @GetMapping("/admin/product/vieworder/{id}")
    public Result viewproduct(@PathVariable int id){
        Result result = new Result();
        Order order = new Order();
        order = orderServiceImpl.getorder(id);
        Result.success(order);
        return result;
    }


}
