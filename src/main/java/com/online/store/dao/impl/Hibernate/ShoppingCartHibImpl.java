package com.online.store.dao.impl.Hibernate;

import com.online.store.dao.ShoppingCartDao;
import com.online.store.model.Item;
import com.online.store.model.ShoppingCart;
import com.online.store.model.User;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ShoppingCartHibImpl implements ShoppingCartDao {

    private static Logger logger = Logger.getLogger(ShoppingCartHibImpl.class);
    private SessionFactory sessionFactory;

    @Autowired
    public ShoppingCartHibImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void createShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart(user);
        sessionFactory.getCurrentSession().save(shoppingCart);
    }

    @Override
    public void addItem(User user, Item item) {
        ShoppingCart shoppingCart = getCartByUser(user);
        shoppingCart.setItems(item);
        sessionFactory.getCurrentSession().update(shoppingCart);
    }

    @Override
    public ShoppingCart getCartByUser(User user) {
        @SuppressWarnings("unchecked")
        TypedQuery<ShoppingCart> query = sessionFactory.getCurrentSession().createQuery("FROM " +
                "ShoppingCart WHERE user = :user");
        query.setParameter("user", user);
        return query.getSingleResult();
    }

    @Override
    public void removeItem(Item item) {
        sessionFactory.getCurrentSession().delete(item);
    }

    @Override
    public void clearShoppingCart(User user) {
        ShoppingCart shoppingCart = getCartByUser(user);
        sessionFactory.getCurrentSession().save(shoppingCart);
    }

    @Override
    public int getSize() {
        return getAll().size();
    }

    @Override
    public List<Item> getAll() {
        @SuppressWarnings("unchecked")
        TypedQuery<Item> query = sessionFactory.getCurrentSession().createQuery("FROM Item ");
        return query.getResultList();
    }

    @Override
    public User getUserById(Long id) {
        @SuppressWarnings("unchecked")
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("FROM " +
                "ShoppingCart WHERE user.id = :id");
        query.setParameter("id", id);
        return query.getSingleResult();
    }
}
