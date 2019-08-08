package com.online.store.dao;

import com.online.store.model.Item;

import java.util.List;

public interface ItemDao {

    void add(Item item);

    List<Item> getAll();

    Item getItem(Long id);

    void removeItem(Item item);

    void replaceItem(Item item);
}
