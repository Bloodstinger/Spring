package com.boraji.tutorial.spring.service;

import java.util.List;

import com.boraji.tutorial.spring.entity.User;

public interface UserService {
    void add(User user);
    List<User> listUsers();
}
