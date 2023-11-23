package com.ouc.mallorder.controller;

import com.ouc.mallcommon.Result;
import com.ouc.mallorder.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("/mycart/get/{userId}")
    public Result getMycart(@PathVariable("userId")int userId)
    {
        return  Result.success();
    }
    @DeleteMapping("/mycart/delete/{orderId}")
    public Result deleteOrderInMyCart(@PathVariable("orderId")int orderId)
    {
        return  Result.success();
    }
    @PutMapping("/mycart/changeAmount/{orderId}")
    public Result changeOrderAmountInMyCart(@PathVariable("orderId")int orderId)
    {
        return  Result.success();
    }
    @GetMapping("/mycart/buy/{userId}")
    public Result confirmBuyOrderInMyCart(@PathVariable("userId") int userId)
    {
        return Result.success();
    }

}
