package com.ouc.malladmin.service;

import com.ouc.mallmbg.model.Indent;
import com.ouc.mallmbg.model.IndentExample;
import com.ouc.mallmbg.model.PageParam;

import java.util.List;

public interface IndentService {
    List<Indent> getindents(PageParam pageParam);
    Indent getindent(Integer id);
    boolean deleteindent(Integer id);
    boolean updateindent(Indent indent);
}
