package com.online.store.service;

import com.online.store.model.Item;

import java.util.List;

public interface ItemService {

    void addItem(String name, String description, double price);

    List<Item> getAll();

    Item getItem(Long id);

    void removeItem(Item item);

    void update(Item item);

}
