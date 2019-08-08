package com.online.store.dao;

import com.online.store.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    void add(User user);

    List<User> getAll();

    Optional<User> getUserById(Long id);

    Optional<User> getUserByEmail(String email);

    void removeUser(User user);

    void replaceUser(User user);
}
