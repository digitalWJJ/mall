package com.ouc.mallorder.service.impl;

import com.ouc.mallmbg.mapper.IndentMapper;
import com.ouc.mallmbg.model.Indent;
import com.ouc.mallmbg.model.IndentExample;
import com.ouc.mallorder.service.IndentService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IndentServiceImpl implements IndentService {
    @Resource
    private IndentMapper indentMapper;

    @Override
    public int deleteItem(int id) {
        return indentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteList(List<Integer> ids) {
        IndentExample indentExample=new IndentExample();
        indentExample.createCriteria().andIdIn(ids);
        return indentMapper.deleteByExample(indentExample);
    }

    @Override
    public int updateAmount(int id, int amount) {
        Indent indent= indentMapper.selectByPrimaryKey(id);
        if(indent==null)return 0;
        indent.setAmount(amount);
        return indentMapper.updateByPrimaryKey(indent);
    }

    @Override
    public List<Indent> getList(int id) {
        IndentExample indentExample=new IndentExample();
        IndentExample.Criteria criteria=indentExample.createCriteria();
        criteria.andUserIdEqualTo(id);
        List<Indent> indentList= indentMapper.selectByExample(indentExample);
        criteria.andStatusEqualTo("待下单");
        return indentList;
    }
    @Override
    public int create(int productId, String color, String configuration, int amount) {
        Indent indent=new Indent();
        indent.setColor(color);
        indent.setProductId(productId);
        indent.setAmount(amount);
        indent.setConfiguration(configuration);
        indent.setCommitTime(new Date(System.currentTimeMillis()));
        indent.setStatus("待下单");
        return indentMapper.insert(indent);

    }
}
