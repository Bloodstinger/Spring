package com.online.store.service.impl;

import com.online.store.dao.ItemDao;
import com.online.store.model.Item;
import com.online.store.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemDao itemDao;

    @Autowired
    public ItemServiceImpl(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    @Transactional
    @Override
    public void addItem(String name, String description, double price) {
        itemDao.save(new Item(name, description, price));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Item> getAll() {
        Iterable<Item> items = itemDao.findAll();
        List<Item> list = new ArrayList<>();
        items.forEach(list::add);
        return list;
    }

    @Transactional(readOnly = true)
    @Override
    public Item getItem(Long id) {
        return itemDao.findById(id).get();
    }

    @Transactional
    @Override
    public void removeItem(Item item) {
        itemDao.delete(item);
    }

    @Transactional
    @Override
    public void update(Item item) {
        itemDao.save(item);
    }
}
