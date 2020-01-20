package com.online.store.service.impl;

import com.online.store.dao.ItemDao;
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

    private final UserDao userDao;
    private final ShoppingCartDao shoppingCartDao;
    private final ItemDao itemDao;

    @Autowired
    public ShoppingCartServiceImpl(UserDao userDao, ShoppingCartDao shoppingCartDao, ItemDao itemDao) {
        this.userDao = userDao;
        this.shoppingCartDao = shoppingCartDao;
        this.itemDao = itemDao;
    }

    @Override
    public double totalPriceCount(List<Item> itemList) {
        double result = 0;
        for (Item item : itemList) {
            result += item.getPrice();
        }
        return result;
    }

    @Transactional
    @Override
    public void createShoppingCart(User user) {
        User persistUser = userDao.findByEmail(user.getEmail()).get();
        ShoppingCart cart = new ShoppingCart(persistUser);
        shoppingCartDao.save(cart);
    }

    @Transactional
    @Override
    public void addItem(User user, Item item) {
        ShoppingCart shoppingCart = shoppingCartDao.getShoppingCartByUser(user);
        shoppingCart.setItems(item);
        shoppingCartDao.save(shoppingCart);
    }

    @Transactional
    @Override
    public void removeItem(User user, Item item) {
        ShoppingCart shoppingCart = shoppingCartDao.getShoppingCartByUser(user);
        List<Item> items = shoppingCart.getItems();
        items.remove(item);
        shoppingCartDao.save(shoppingCart);
    }

    @Transactional(readOnly = true)
    @Override
    public int getSize() {
        return (int) shoppingCartDao.count();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Item> getAll(User user) {
        ShoppingCart shoppingCart = shoppingCartDao.getShoppingCartByUser(user);
        return shoppingCart.getItems();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> getUserById(Long id) {
        return userDao.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public ShoppingCart getCartByUser(User user) {
        try {
            return shoppingCartDao.getShoppingCartByUser(user);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    @Override
    public void clearShoppingCart(User user) {
        ShoppingCart shoppingCart = shoppingCartDao.getShoppingCartByUser(user);
        List<Item> items = shoppingCart.getItems();
        items.clear();
        shoppingCartDao.save(shoppingCart);
    }
}
