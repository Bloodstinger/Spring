package com.online.store.service.impl;

import com.online.store.dao.ItemDao;
import com.online.store.model.Item;
import com.online.store.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        itemDao.add(new Item(name, description, price));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Item> getAll() {
        return itemDao.getAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Item getItem(Long id) {
        return itemDao.getItem(id);
    }

    @Transactional
    @Override
    public void removeItem(Item item) {
        itemDao.removeItem(item);
    }

    @Transactional
    @Override
    public void update(Item item) {
        itemDao.replaceItem(item);
    }
}
