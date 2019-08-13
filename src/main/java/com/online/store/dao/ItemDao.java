package com.online.store.dao;

import com.online.store.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemDao extends CrudRepository<Item, Long> {

}
