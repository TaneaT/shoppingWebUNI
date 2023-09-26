package com.practiceUni.shoppingWeb.dao.impl;

import com.practiceUni.shoppingWeb.JdbcConnection;
import com.practiceUni.shoppingWeb.dao.UserDAO;
import com.practiceUni.shoppingWeb.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDAOImplTest {

  @Autowired private UserDAO userDAO;

  private User getTestUser() {
    return new User("firstName", "lastName", "log7in", "password", "em7ail", "address");
  }



  @Test
  void shouldCreateUser() {
    User user = getTestUser();
    assertNotNull(userDAO.create(user));

    User testUser = userDAO.findById(user.getId());
    assertNotNull(testUser);
    assertEquals(testUser, user);

    assertTrue(userDAO.deleteById(testUser.getId()));
  }

  @Test
  void shouldUpdateUser() {
    User user = getTestUser();
    assertNotNull(userDAO.create(user));

    user.setAddress("new Address");

    User updatedUser = userDAO.update(user);
    assertNotNull(updatedUser);

    User foundUser = userDAO.findById(updatedUser.getId());
    assertNotNull(foundUser);
    assertEquals(foundUser, updatedUser);

    assertTrue(userDAO.deleteById(user.getId()));
  }

  @Test
  void shouldCreateUserIfNotFound(){
    User user = getTestUser();

    assertNull(userDAO.findById(user.getId()));

    userDAO.create(user);
    assertNotNull(user);

    User testUser = userDAO.findById(user.getId());
    assertNotNull(testUser);
    assertEquals(testUser,user);

    boolean isDeleted = userDAO.deleteById(user.getId());
    assertTrue(isDeleted);

  }


  @Test
  void shouldDeleteUserById() {
    User user = getTestUser();
    assertNotNull(userDAO.create(user));

    assertTrue(userDAO.deleteById(user.getId()));
  }

  @Test
  void shouldFindUserById() {
    User user = getTestUser();
    assertNotNull(userDAO.create(user));

    User testUser = userDAO.findById(user.getId());
    assertNotNull(testUser);
    assertEquals(testUser, user);

    boolean isDeleted = userDAO.deleteById(user.getId());
    assertTrue(isDeleted);
  }

  @Test
  void shouldFindByLogin() {
    User user = getTestUser();
    assertNotNull(userDAO.create(user));

    User testUser = userDAO.findByLogin(user.getLogin());
    assertNotNull(testUser);
    assertEquals(testUser, user);

    boolean isDeleted = userDAO.deleteById(user.getId());
    assertTrue(isDeleted);
  }
}
