package com.online.store.service.impl;

import com.online.store.dao.OrderDao;
import com.online.store.dao.UserDao;
import com.online.store.model.Order;
import com.online.store.model.ShoppingCart;
import com.online.store.model.User;
import com.online.store.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;
    private final UserDao userDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao, UserDao userDao) {
        this.orderDao = orderDao;
        this.userDao = userDao;
    }

    @Override
    public void addOrder(User user, ShoppingCart shoppingCart) {
        User persistUser = userDao.findByEmail(user.getEmail()).get();
        Order order = new Order(persistUser, shoppingCart);
        orderDao.save(order);
    }
}
