package com.online.store.dao;

import com.online.store.model.Item;
import com.online.store.model.ShoppingCart;
import com.online.store.model.User;

import java.util.List;

public interface ShoppingCartDao {

    void createShoppingCart(User user);

    void addItem(User user, Item item);

    ShoppingCart getCartByUser(User user);

    void removeItem(Item item);

    void clearShoppingCart(User user);

    int getSize();

    List<Item> getAll();

    User getUserById(Long id);
}
