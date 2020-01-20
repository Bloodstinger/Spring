package com.online.store.service;

import com.online.store.model.ShoppingCart;
import com.online.store.model.User;

public interface OrderService {

    void addOrder(User user, ShoppingCart shoppingCart);

}
