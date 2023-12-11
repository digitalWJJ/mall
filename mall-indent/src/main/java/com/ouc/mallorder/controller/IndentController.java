package com.ouc.mallorder.controller;

import com.ouc.mallcommon.Result;
import com.ouc.mallcommon.utils.TokenUtils;
import com.ouc.mallmbg.model.Indent;
import com.ouc.mallorder.service.IndentService;
import dto.ProductIdsAndOtherInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/mycart")
public class IndentController {
    @Resource
    private IndentService indentService;

    @RequestMapping (value = "/get/",method = RequestMethod.GET)
    @ResponseBody
    public Result getOrdersInMyCart()
    {
        int userId=TokenUtils.getCurrentUser().getId();
        List<Indent> indentList= indentService.getList(userId);
        if(indentList.size()==0)
            return Result.result(500,"获取不到数据",null);
        Result result=new Result(200,"获取购物车成功",indentList);
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
    @RequestMapping(value = "/add/{productId}",method = RequestMethod.POST)
    @ResponseBody
    public Result addProductIntoMyCart(@PathVariable("productId") int productId,String color,String configuration,int amount)
    {
        if(indentService.create(productId,color,configuration,amount)>0)
            return Result.result(200,"添加购物车成功",null);
        else return  Result.result(500,"添加购物车失败",null);
    }

    @RequestMapping(value = "/confirmList/",method = RequestMethod.POST)
    @ResponseBody
    public Result confirmBuyProducts(@RequestBody List<Integer> ids)
    {
        List<Indent> indentList=indentService.getListByIds(ids);
        if(indentList.size()!=ids.size())
        {
            return Result.result(500,"确认订单失败",null);
        }
        return Result.result(200,"确认订单成功",indentList);
    }

    @RequestMapping(value = "/confirmOne/",method = RequestMethod.POST)
    @ResponseBody
    public Result confirmBuyOneProduct(@RequestBody Integer id)
    {
        Indent indent=indentService.getItem(id);
        if(indent==null)
        {
            return Result.result(500,"确认订单失败",null);
        }
        return Result.result(200,"确认订单成功",indent);
    }

    @RequestMapping(value = "/deleteList",method = RequestMethod.DELETE)
    @ResponseBody
    public Result deleteMultipleOrdersInMyCart(@RequestBody List<Integer> ids)
    {
        if(indentService.deleteList(ids)>0)
        {
            return Result.result(200,"删除多个订单条目成功",null);
        }
        return Result.result(500,"删除多个订单条目失败",null);
    }

    @RequestMapping (value = "/allIndent/get/",method = RequestMethod.GET)
    @ResponseBody
    public Result getAllIndent()
    {
        int userId=TokenUtils.getCurrentUser().getId();
        List<Indent> indentList= indentService.getAllList(userId);
        if(indentList.size()==0)
            return Result.result(500,"获取不到数据",null);
        Result result=new Result(200,"获取用户所有订单成功",indentList);
        return result;
    }

    @RequestMapping (value = "/mycart/buy",method = RequestMethod.POST)
    @ResponseBody
    public Result buyIndentsInMyCart(@RequestBody ProductIdsAndOtherInfo productIdsAndOtherInfo)
    {
        if(indentService.updateAfterBuy(productIdsAndOtherInfo)<productIdsAndOtherInfo.getIndentIds().size())
            return Result.result(500,"购买失败",null);
        Result result=new Result(200,"购买成功",null);
        return result;
    }
}
