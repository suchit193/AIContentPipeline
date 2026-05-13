package com.contentflow.service;

import com.contentflow.dao.UserDao;
import com.contentflow.model.User;

public class AuthService {

    UserDao userDao = new UserDao();

    // REGISTER

    public boolean register(User user) {

        return userDao.registerUser(user);
    }

    // CHECK USER EXISTS

    public boolean userExists(String email) {

        return userDao.checkUserExists(email);
    }

    // LOGIN

    public User login(String email,
                      String password) {

        return userDao
                .loginUser(email, password);
    }
}