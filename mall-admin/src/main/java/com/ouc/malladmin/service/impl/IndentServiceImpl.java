package com.ouc.malladmin.service.impl;

import com.ouc.malladmin.service.IndentService;
import com.ouc.mallmbg.mapper.IndentMapper;
import com.ouc.mallmbg.model.Indent;
import com.ouc.mallmbg.model.IndentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IndentServiceImpl implements IndentService {
    @Autowired
    IndentMapper indentMapper;
    @Override
    public List<Indent> getindents(IndentExample indentExample){
        List<Indent> indents = new ArrayList<Indent>();
        indents = indentMapper.selectByExample(indentExample);
        return indents;
    }
    @Override
    public Indent getindent(Integer id){
        Indent indent = new Indent();
        indent = indentMapper.selectByPrimaryKey(id);
        return indent;
    }
    @Override
    public  void deleteindent(Integer id){
        indentMapper.deleteByPrimaryKey(id);
    }
    @Override
    public void updateindent(Indent indent){
        indentMapper.updateByPrimaryKeySelective(indent);
    }
}
