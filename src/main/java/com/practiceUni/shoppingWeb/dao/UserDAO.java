package com.practiceUni.shoppingWeb.dao;

import com.practiceUni.shoppingWeb.domain.User;


public interface UserDAO extends DAO<User>{

    User create (User user);

    User update(User user);

    User updatePassword(User user);

    boolean deleteById (Integer id);

    User findById(Integer id);

    User findByLogin(String login);







}
