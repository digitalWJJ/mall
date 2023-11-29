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
@RestController
@RequestMapping(value = "/api/admin/order")
public class OrderController {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderServiceImpl orderServiceImpl;
    @GetMapping("/vieworders")
    public Result vieworders(){
        List<Order> orderList = new ArrayList<Order>();
        OrderExample orderExample = new OrderExample();
        orderList = orderServiceImpl.getorders(orderExample);
        if(orderList==null) {
            return Result.result(500,"获取订单列表失败",null);
        }
        else return Result.success(orderList);
    }
    @GetMapping("/vieworder/{id}")
    public Result vieworeder(@PathVariable int id){
        Order order = new Order();
        order = orderServiceImpl.getorder(id);
        if(order==null) {
            return Result.result(500,"获取订单信息失败",null);
        }
        else return Result.success(order);
    }
    @PutMapping("/updateorder/{id}")
    public Result updateorder(@RequestBody Order order){
        orderServiceImpl.updateorder(order);
        return Result.result(200, "更新成功", null)
    }
    @DeleteMapping("/deleteorder/{id}")
    public Result deleteorder(@PathVariable int id){
        orderServiceImpl.deleteorder(id);
        return Result.result(200, "删除成功", null);
    }


}
