package com.practiceUni.shoppingWeb.service.impl;

import com.practiceUni.shoppingWeb.dao.UserDAO;
import com.practiceUni.shoppingWeb.domain.User;
import com.practiceUni.shoppingWeb.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

  private final UserDAO userDAO;

  public UserServiceImpl(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

@Override
  public User authenticateUser(String login, String password) {
    User user = userDAO.findByLogin(login);

    if (user != null && user.getPassword().equals(password)) {
      return user;
    }

    return null;
  }


  @Override
  public User createUser(User user) {
    return userDAO.create(user);
  }


  @Override
  public User updateUser(User user) {
    if (user.getId() == null) {
      return userDAO.create(user);
    } else {
      return userDAO.update(user);
    }
  }

  @Override
  public User updateUserPassword(User user) {
    if (user.getId() == null) {
      return userDAO.create(user);
    } else {
      return userDAO.updatePassword(user);
    }
  }

  @Override
  public boolean deleteUserById(Integer id) {
    if (id == null) {
      LOGGER.error("Failed to delete user due to null Id");
      return false;
    }
    return userDAO.deleteById(id);
  }

  @Override
  public User findUserById(Integer id) {

    User user = userDAO.findById(id);
    if (user == null) {
      LOGGER.error("Failed to find a user");
      return null;
    } else {
      return user;
    }
  }

  @Override
  public User findUserByLogin(String login) {
    User user = userDAO.findByLogin(login);

    if (user == null) {
      LOGGER.error("Failed to find a user due to wrong login: " + login);
      return null;
    } else {
      return user;
    }
  }
}
