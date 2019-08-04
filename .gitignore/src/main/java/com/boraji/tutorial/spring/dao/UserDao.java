package com.boraji.tutorial.spring.dao;

import java.util.List;

import com.boraji.tutorial.spring.entity.User;

public interface UserDao {
   void add(User user);
   List<User> listUsers();
}
