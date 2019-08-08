package com.online.store.service;

import com.online.store.model.Item;
import com.online.store.model.Order;

import java.util.List;

public interface OrderService {

    void addOrder(Order order);

    List<Item> getOrderItems();
}
