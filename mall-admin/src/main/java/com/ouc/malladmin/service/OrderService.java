package com.ouc.malladmin.service;

import com.ouc.mallmbg.model.Order;
import com.ouc.mallmbg.model.OrderExample;
import com.ouc.mallmbg.model.ProductExample;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface OrderService {
    List<Order> getorders(OrderExample orderExample);
    Order getorder(Integer id);
    void deleteorder(Integer id);
    void updateorder(Order order);
}
