package com.ouc.mallorder.controller;

import com.ouc.mallcommon.Result;
import com.ouc.mallmbg.model.Indent;
import com.ouc.mallorder.service.IndentService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/mycart")
public class IndentController {
    @Resource
    private IndentService indentService;

    @RequestMapping (value = "/get/{userId}",method = RequestMethod.GET)
    @ResponseBody
    public Result getOrdersInMyCart(@PathVariable("userId")int userId)
    {
        List<Indent> indentList= indentService.getList(userId);
        if(indentList.size()==0)
            return Result.result(500,"获取不到数据",null);
        Result result=new Result(200,"获取购车车成功",indentList);
        return result;
    }
    @RequestMapping(value = "/delete/{orderId}",method = RequestMethod.DELETE)
    @ResponseBody
    public Result deleteOrderInMyCart(@PathVariable("orderId")int orderId)
    {
        if(indentService.deleteItem(orderId)>0)
        {
            return Result.result(200,"删除购物车条目成功",null);
        }
        else
        {
            return  Result.result(500,"删除购物车条目失败",null);
        }
    }
    @RequestMapping(value = "/changeAmount/{orderId}",method = RequestMethod.PUT)
    @ResponseBody
    public Result changeOrderAmountInMyCart(@PathVariable("orderId")int orderId,int amount)
    {
        if(indentService.updateAmount(orderId,amount)>0)
        {
            return  Result.result(200,"更改数量成功",null);
        }
        else
        {
           return  Result.result(500,"更改数量失败",null);
        }
    }
    @RequestMapping(value = "/buy/{userId}",method = RequestMethod.GET)
    @ResponseBody
    public Result confirmBuyOrderInMyCart(@PathVariable("userId") int userId)
    {
        List<Indent> indentList= indentService.getList(userId);
        if(indentList.size()==0)
            return Result.result(500,"获取不到数据",null);
        return Result.result(200,"购买成功",indentList);
    }

    @RequestMapping(value = "/add/{productId}",method = RequestMethod.POST)
    @ResponseBody
    public Result addProductIntoMyCart(@PathVariable("productId") int productId,String color,String configuration,int amount)
    {
        if(indentService.create(productId,color,configuration,amount)>0)
            return Result.result(200,"添加购物车成功",null);
        else return  Result.result(500,"添加购物车失败",null);
    }

    @RequestMapping(value = "/confirm/deleteList",method = RequestMethod.DELETE)
    @ResponseBody
    public Result deleteMultipleOrdersInMyCart(List<Integer> ids)
    {
        if(indentService.deleteList(ids)>0)
        {
            return Result.result(200,"删除多个订单条目成功",null);
        }
        return Result.result(500,"删除多个订单条目失败",null);
    }
}
