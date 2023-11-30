package com.ouc.malladmin.controller;

import com.ouc.malladmin.service.IndentService;
import com.ouc.mallcommon.Result;
import com.ouc.mallmbg.mapper.IndentMapper;
import com.ouc.mallmbg.model.Indent;
import com.ouc.mallmbg.model.IndentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping(value = "/api/admin/order")
public class IndentController {
    @Autowired
    IndentMapper indentMapper;
    @Autowired
    IndentService IndentService;
    @GetMapping("/vieworders")
    public Result vieworders(){
        List<Indent> IndentList = new ArrayList<>();
        IndentExample IndentExample = new IndentExample();
        IndentList = IndentService.getindents(IndentExample);
        if(IndentList==null) {
            return Result.result(500,"获取订单列表失败",null);
        }
        else return Result.success(IndentList);
    }
    @GetMapping("/vieworder/{id}")
    public Result vieworeder(@PathVariable int id){
        Indent Indent = new Indent();
        Indent = IndentService.getindent(id);
        if(Indent==null) {
            return Result.result(500,"获取订单信息失败",null);
        }
        else return Result.success(Indent);
    }
    @PostMapping("/updateorder")
    public Result updateIndent(@RequestBody Indent Indent){
        IndentService.updateindent(Indent);
        return Result.result(200, "更新订单成功", null);
    }
    @DeleteMapping("/deleteorder/{id}")
    public Result deleteIndent(@PathVariable int id){
        IndentService.deleteindent(id);
        return Result.result(200, "删除订单成功", null);
    }


}
