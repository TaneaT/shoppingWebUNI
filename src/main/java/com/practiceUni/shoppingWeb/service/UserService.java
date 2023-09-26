package com.practiceUni.shoppingWeb.service;

import com.practiceUni.shoppingWeb.domain.User;

public interface UserService {

User authenticateUser(String login, String password);
    User createUser (User user);

    User updateUser (User user);

    User updateUserPassword(User user);

    boolean deleteUserById(Integer id);

    User findUserById(Integer id);

    User findUserByLogin(String login);


}
