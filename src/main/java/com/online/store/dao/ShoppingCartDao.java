package com.online.store.dao;

import com.online.store.model.Item;
import com.online.store.model.ShoppingCart;
import com.online.store.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartDao extends CrudRepository<ShoppingCart, Long> {
//
//    void addItem(User user, Item item);//TODO
//
//    void deleteItemFromCart(Item item);
//
//    List<Item> getItemsFromShoppingCart();
//
//    ShoppingCart getCartByUser(User user);
//
//    void clearShoppingCart(User user);
}
