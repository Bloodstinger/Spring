package com.online.store.dao;

import com.online.store.model.Item;
import com.online.store.model.Order;

import java.util.List;

public interface OrderDao {

    void addOrder(Order order);

    List<Item> getItems();

}
