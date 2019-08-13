package com.online.store.service;

import com.online.store.dao.UserDao;
import com.online.store.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public UserDetailsServiceImpl() {
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = findUserByEmail(s);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    private User findUserByEmail(String email) {
//        return new User("root@localhost", encoder.encode("root"), "ROLE_ADMIN");
        return userService.getUserByEmail(email).get();
    }
}
