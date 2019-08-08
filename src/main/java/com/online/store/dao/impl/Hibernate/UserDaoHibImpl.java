package com.online.store.dao.impl.Hibernate;

import com.online.store.dao.UserDao;
import com.online.store.model.User;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoHibImpl implements UserDao {

    private static Logger logger = Logger.getLogger(UserDaoHibImpl.class);
    private SessionFactory sessionFactory;

    @Autowired
    public UserDaoHibImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> getAll() {
        @SuppressWarnings("unchecked")
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("FROM User ");
        return query.getResultList();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        try {
            @SuppressWarnings("unchecked")
            TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("FROM User WHERE " +
                    "id = :id");
            query.setParameter("id", id);
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        try {
            @SuppressWarnings("unchecked")
            TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("FROM User WHERE " +
                    "email = :email");
            query.setParameter("email", email);
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public void removeUser(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public void replaceUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }
}
