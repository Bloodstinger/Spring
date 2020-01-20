package com.online.store.dao;

import com.online.store.model.ShoppingCart;
import com.online.store.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartDao extends CrudRepository<ShoppingCart, Long> {

    ShoppingCart getShoppingCartByUser(User user);

}
