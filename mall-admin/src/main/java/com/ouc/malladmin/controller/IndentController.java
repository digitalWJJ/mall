package com.ouc.malladmin.controller;

import com.ouc.malladmin.service.IndentService;
import com.ouc.mallcommon.Result;
import com.ouc.mallmbg.mapper.IndentMapper;
import com.ouc.mallmbg.model.Indent;
import com.ouc.mallmbg.model.IndentExample;
import com.ouc.mallmbg.model.PageParam;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping(value = "/api/admin/order")
public class IndentController {
    @Resource(type = com.ouc.malladmin.service.impl.IndentServiceImpl.class)
    IndentService IndentService;
    @PostMapping("/vieworders")
    public Result vieworders(@RequestBody PageParam pageParam){
        List<Indent> IndentList = IndentService.getindents(pageParam);
        if(IndentList.isEmpty()) return Result.result(402,"获取订单列表失败,订单列表为空",null);
        else return Result.success(IndentList);
    }
    @GetMapping("/vieworder/{id}")
    public Result vieworeder(@PathVariable int id){
        Indent Indent = IndentService.getindent(id);
        if(Indent == null) return Result.result(402,"获取订单信息失败,不存在该订单，",null);
        else return Result.success(Indent);
    }
    @PostMapping("/updateorder")
    public Result updateIndent(@RequestBody Indent Indent){
        if(IndentService.updateindent(Indent)) return Result.result(200, "更新订单成功", null);
        else return Result.result(402, "更新失败，请重新操作", null);
    }
    @DeleteMapping("/deleteorder/{id}")
    public Result deleteIndent(@PathVariable int id){
        if(IndentService.deleteindent(id)) return Result.result(200, "删除订单成功", null);
        else return Result.result(402, "删除失败，请重新操作", null);
    }
}
