package com.online.store.dao.impl.Hibernate;

import com.online.store.dao.ItemDao;
import com.online.store.model.Item;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ItemDaoHibImpl implements ItemDao {

    private static final Logger logger = Logger.getLogger(ItemDaoHibImpl.class);
    private SessionFactory sessionFactory;

    @Autowired
    public ItemDaoHibImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Item item) {
        sessionFactory.getCurrentSession().save(item);
    }

    @Override
    public List<Item> getAll() {
        @SuppressWarnings("unchecked")
        TypedQuery<Item> query = sessionFactory.getCurrentSession().createQuery("FROM Item");
        return query.getResultList();
    }

    @Override
    public Item getItem(Long id) {
        @SuppressWarnings({"unchecked"})
        TypedQuery<Item> query = sessionFactory.getCurrentSession().
                createQuery("FROM Item WHERE id = :id");
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void removeItem(Item item) {
        sessionFactory.getCurrentSession().delete(item);
    }

    @Override
    public void replaceItem(Item item) {
        sessionFactory.getCurrentSession().update(item);
    }
}
