package com.practiceUni.shoppingWeb.controller;

import com.practiceUni.shoppingWeb.domain.User;
import com.practiceUni.shoppingWeb.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserServiceImpl userServiceImpl;

    private UserController userController;

    @BeforeEach
    void setUp() {
        userController = new UserController(userServiceImpl);
      }

      private User getTestUser(){
        return new User(1,"firstName", "lastName", "login", "password", "email", "address");
      }

    @Test
        void shouldCreateUser() {
        User user = getTestUser();

        Mockito.when(userServiceImpl.createUser(user)).thenReturn(user);
        assertNotNull(userController.createUser(user));

        Mockito.when(userServiceImpl.findUserById(user.getId())).thenReturn(user);
        User testUser = userController.findUserById(user.getId());
        assertNotNull(testUser);
        assertEquals(testUser,user);

      }

    @Test
    void shouldUpdateUser() {
        User user = getTestUser();

        Mockito.when(userServiceImpl.createUser(user)).thenReturn(user);
        assertNotNull(userController.createUser(user));

        user.setAddress("New Address");

        Mockito.when(userServiceImpl.updateUser(user)).thenReturn(user);
        User updatedUser = userController.updateUser(user);
        assertNotNull(updatedUser);

        Mockito.when(userServiceImpl.findUserById(user.getId())).thenReturn(user);
        User testUser =userController.findUserById(user.getId());
        assertNotNull(testUser);
        assertEquals(testUser,updatedUser);

      }

      @Test
      void shouldCreateUserIfNotFound(){
        User user = new User("firstName", "lastName", "login", "password", "email", "address");

        Mockito.when(userServiceImpl.findUserById(user.getId())).thenReturn(null);
        assertNull(userController.findUserById(user.getId()));

        Mockito.when(userServiceImpl.createUser(user)).thenReturn(user);
        user.setId(1);
        assertNotNull(userController.createUser(user));

        Mockito.when(userServiceImpl.findUserById(user.getId())).thenReturn(user);
        User testUser = userController.findUserById(user.getId());
        assertNotNull(testUser);
        assertEquals(testUser,user);

      }

    @Test
    void shouldDeleteUserById() {
        User user = getTestUser();

        Mockito.when(userServiceImpl.createUser(user)).thenReturn(user);
        assertNotNull(userController.createUser(user));

        Mockito.when(userServiceImpl.deleteUserById(user.getId())).thenReturn(true);
        assertTrue(userController.deleteUserById(user.getId()));
  }

    @Test
    void findUserById() {
        User user = getTestUser();

        Mockito.when(userServiceImpl.createUser(user)).thenReturn(user);
        assertNotNull(userController.createUser(user));

        Mockito.when(userServiceImpl.findUserById(user.getId())).thenReturn(user);
        User foundUser = userController.findUserById(user.getId());
        assertNotNull(foundUser);
        assertEquals(foundUser,user);

      }

    @Test
    void findUserByLogin() {
        User user = getTestUser();

        Mockito.when(userServiceImpl.createUser(user)).thenReturn(user);
        assertNotNull(userController.createUser(user));

        Mockito.when(userServiceImpl.findUserByLogin(user.getLogin())).thenReturn(user);
        User foundUser = userController.findUserByLogin(user.getLogin());
        assertNotNull(foundUser);
        assertEquals(foundUser,user);

      }
}