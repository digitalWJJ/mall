package com.ouc.mallorder.service;

import java.util.List;
import com.ouc.mallmbg.model.Order;

public interface OrderService {
    List<Order> getMyCart(int id);
    boolean deleteProduct(int id);

    boolean changeProductAmount(int id,int amount);

    List<Order> confirmBuyOrder(int userId);
}
