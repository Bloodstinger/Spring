package com.online.store.service;

import com.online.store.model.Item;
import com.online.store.model.ShoppingCart;
import com.online.store.model.User;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartService {

    double totalPriceCount(List<Item> itemList);

    void createShoppingCart(User user);

    void addItem(User user, Item item);

    void removeItem(User user, Item item);

    int getSize();

    List<Item> getAll(User user);

    Optional<User> getUserById(Long id);

    ShoppingCart getCartByUser(User user);

    void clearShoppingCart(User user);

}
