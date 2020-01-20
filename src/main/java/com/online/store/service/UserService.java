package com.online.store.service;

import com.online.store.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    boolean inDatabase(String email);

    void addUser(String email, String password, String role);

    List<User> getAll();

    Optional<User> getUserById(Long id);

    Optional<User> getUserByEmail(String email);

    void removeUser(User user);

    void update(User user);

    boolean isParamEmpty(String email, String password, String rPassword, String role);

    void updateUser(String email, String password, String role);

}
