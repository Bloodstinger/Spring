package com.online.store.service.impl;

import com.online.store.dao.ShoppingCartDao;
import com.online.store.dao.UserDao;
import com.online.store.model.Item;
import com.online.store.model.ShoppingCart;
import com.online.store.model.User;
import com.online.store.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private UserDao userDao;
    private ShoppingCartDao shoppingCartDao;

    @Autowired
    public ShoppingCartServiceImpl(UserDao userDao, ShoppingCartDao shoppingCartDao) {
        this.userDao = userDao;
        this.shoppingCartDao = shoppingCartDao;
    }

    @Transactional
    @Override
    public void createShoppingCart(User user) {
        shoppingCartDao.createShoppingCart(user);
    }

    @Transactional
    @Override
    public void addItem(User user, Item item) {
        shoppingCartDao.addItem(user, item);
    }

    @Transactional
    @Override
    public void removeItem(Item item) {
        shoppingCartDao.removeItem(item);
    }

    @Transactional(readOnly = true)
    @Override
    public int getSize() {
        return shoppingCartDao.getSize();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Item> getAll() {
        return shoppingCartDao.getAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public ShoppingCart getCartByUser(User user) {
        try {
            return shoppingCartDao.getCartByUser(user);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    @Override
    public void clearShoppingCart(User user) {
        shoppingCartDao.clearShoppingCart(user);
    }
}
