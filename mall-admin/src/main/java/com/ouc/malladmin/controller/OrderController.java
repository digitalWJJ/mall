package com.ouc.malladmin.controller;

import com.ouc.malladmin.model.ProductModel;
import com.ouc.malladmin.service.impl.OrderServiceImpl;
import com.ouc.mallcommon.Result;
import com.ouc.mallmbg.mapper.OrderMapper;
import com.ouc.mallmbg.model.Order;
import com.ouc.mallmbg.model.OrderExample;
import com.ouc.mallmbg.model.Product;
import com.ouc.mallmbg.model.ProductExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public class OrderController {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderServiceImpl orderServiceImpl;
    @GetMapping("/admin/order/vieworders")
    public Result vieworders(){
        Result result = new Result();
        List<Order> orderList = new ArrayList<Order>();
        OrderExample orderExample = new OrderExample();
        orderList = orderServiceImpl.getorders(orderExample);
        Result.success(orderList);
        return result;
    }
    @GetMapping("/admin/product/vieworder/{id}")
    public Result vieworeder(@PathVariable int id){
        Result result = new Result();
        Order order = new Order();
        order = orderServiceImpl.getorder(id);
        Result.success(order);
        return result;
    }

    @PutMapping("/admin/product/updateorder/{id}")
    public Result updateorder(@RequestBody Order order){
        Result result = new Result();
        orderServiceImpl.updateorder(order);
        Result.success();
        return result;
    }
    @DeleteMapping("/admin/product/deleteorder/{id}")
    public Result deleteorder(@PathVariable int id){
        orderServiceImpl.deleteorder(id);
        Result result = new Result();
        Result.success();
        return result;
    }


}
