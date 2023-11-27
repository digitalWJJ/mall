package com.ouc.mallorder.service;

import java.util.List;

import com.ouc.mallmbg.model.Indent;

public interface IndentService {
    int deleteItem(int id);

    int updateAmount(int id, int amount);

    List<Indent> getList(int userId);
    int create(int id, String color, String configuration, int amount);

    int deleteList(List<Integer> ids);
}
