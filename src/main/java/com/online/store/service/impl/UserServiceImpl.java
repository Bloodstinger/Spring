package com.online.store.service.impl;

import com.online.store.dao.UserDao;
import com.online.store.model.User;
import com.online.store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, BCryptPasswordEncoder encoder) {
        this.userDao = userDao;
        this.encoder = encoder;
    }

    @Transactional(readOnly = true)
    @Override
    public boolean inDatabase(String email) {
        return userDao.findByEmail(email).isPresent();
    }

    @Transactional
    @Override
    public void addUser(String email, String password, String role) {
        String encodedPassword = encoder.encode(password);
        userDao.save(new User(email, encodedPassword, role));
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAll() {
        Iterable<User> users = userDao.findAll();
        List<User> list = new ArrayList<>();
        users.forEach(list::add);
        return list;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> getUserById(Long id) {
        return userDao.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<User> getUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Transactional
    @Override
    public void removeUser(User user) {
        userDao.delete(user);
    }

    @Transactional
    @Override
    public void update(User user) {
        userDao.save(user);
    }

    @Override
    public boolean isParamEmpty(String email, String password, String rPassword, String role) {
        return !email.isEmpty() && !password.isEmpty() && !rPassword.isEmpty() && role != null;
    }

    @Transactional
    @Override
    public void updateUser(String email, String password, String role) {
        User userToUpdate = userDao.findByEmail(email).get();
        String encodedPassword = encoder.encode(password);
        userToUpdate.setEmail(email);
        userToUpdate.setPassword(encodedPassword);
        userToUpdate.setRole(role);
        userDao.save(userToUpdate);
    }

}
