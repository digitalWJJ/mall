package com.ouc.malladmin.service.impl;

import com.ouc.malladmin.service.IndentService;
import com.ouc.mallcommon.tools.CacheTool;
import com.ouc.mallmbg.mapper.IndentMapper;
import com.ouc.mallmbg.model.Indent;
import com.ouc.mallmbg.model.IndentExample;
import com.ouc.mallmbg.model.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IndentServiceImpl implements IndentService {
    @Autowired
    IndentMapper indentMapper;
    @Override
    public List<Indent> getindents(PageParam pageParam){
        return CacheTool.getIndents(pageParam);
    }
    @Override
    public Indent getindent(Integer id){
        return CacheTool.getIndent(id);
    }
    @Override
    public boolean deleteindent(Integer id){
        return CacheTool.deleteIndent(id);
    }
    @Override
    public boolean updateindent(Indent indent){
        return CacheTool.updateIndent(indent);
    }
}
