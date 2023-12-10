package com.ouc.mallorder.service;

import java.util.List;

import com.ouc.mallmbg.model.Indent;
import dto.ProductIdsAndOtherInfo;

public interface IndentService {

    Indent getItem(int id);
    int deleteItem(int id);

    int updateAmount(int id, int amount);

    List<Indent> getList(int userId);
    int create(int id, String color, String configuration, int amount);

    List<Indent> getAllList(int id);

    List<Indent> getListByIds(List<Integer> ids);

    int deleteList(List<Integer> ids);

    int updateAfterBuy(ProductIdsAndOtherInfo productIdsAndOtherInfo);
}
