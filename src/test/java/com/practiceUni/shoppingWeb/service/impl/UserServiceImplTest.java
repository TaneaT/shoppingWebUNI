package com.practiceUni.shoppingWeb.service.impl;

import com.practiceUni.shoppingWeb.dao.impl.UserDAOImpl;
import com.practiceUni.shoppingWeb.domain.User;
import com.practiceUni.shoppingWeb.service.UserService;
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
class UserServiceImplTest {

    @Mock
    private UserDAOImpl userDAOImpl;

    private UserServiceImpl userServiceImpl;


    @BeforeEach
    void setUp() {
        userServiceImpl = new UserServiceImpl(userDAOImpl);
      }

      private User getTestUser(){
        return new User(1,"firstName", "lastName", "login", "password", "email", "address");
      }


    @Test
    void shouldCreateUser() {
        User user = getTestUser();

        Mockito.when(userDAOImpl.create(user)).thenReturn(user);
        assertNotNull(userServiceImpl.createUser(user));

        Mockito.when(userDAOImpl.findById(user.getId())).thenReturn(user);
        User testUser = userServiceImpl.findUserById(user.getId());
        assertNotNull(testUser);
        assertEquals(testUser,user);

      }

    @Test
    void shouldUpdateUser() {
        User user = getTestUser();

        Mockito.when(userDAOImpl.create(user)).thenReturn(user);
        assertNotNull(userServiceImpl.createUser(user));

        user.setAddress("New Address");

        Mockito.when(userDAOImpl.update(user)).thenReturn(user);
        User updatedUser = userServiceImpl.updateUser(user);
        assertNotNull(updatedUser);

        Mockito.when(userDAOImpl.findById(user.getId())).thenReturn(user);
        User testUser = userServiceImpl.findUserById(updatedUser.getId());
        assertNotNull(testUser);
        assertEquals(testUser,updatedUser);
      }

      @Test
      void shouldCreateUserIfNotFound(){
        User user = new User("firstName", "lastName", "login", "password", "email", "address");

        Mockito.when(userDAOImpl.findById(user.getId())).thenReturn(null);
        assertNull(userServiceImpl.findUserById(user.getId()));

        Mockito.when(userDAOImpl.create(user)).thenReturn(user);
        user.setId(1);
        assertNotNull(userServiceImpl.createUser(user));

        Mockito.when(userDAOImpl.findById(user.getId())).thenReturn(user);
        User testUser = userServiceImpl.findUserById(user.getId());
        assertNotNull(testUser);
        assertEquals(testUser,user);

      }

    @Test
    void shouldDeleteUserById() {
        User user = getTestUser();

        Mockito.when(userDAOImpl.create(user)).thenReturn(user);
        assertNotNull(userServiceImpl.createUser(user));

        Mockito.when(userDAOImpl.deleteById(user.getId())).thenReturn(true);
        assertTrue(userServiceImpl.deleteUserById(user.getId()));

      }

    @Test
    void shouldFindUserById() {
        User user = getTestUser();

        Mockito.when(userDAOImpl.create(user)).thenReturn(user);
        assertNotNull(userServiceImpl.createUser(user));

        Mockito.when(userDAOImpl.findById(user.getId())).thenReturn(user);
        User foundUser = userServiceImpl.findUserById(user.getId());
        assertNotNull(foundUser);
        assertEquals(foundUser,user);

      }

    @Test
    void shouldFindUserByLogin() {
        User user = getTestUser();

        Mockito.when(userDAOImpl.create(user)).thenReturn(user);
        assertNotNull(userServiceImpl.createUser(user));

        Mockito.when(userDAOImpl.findByLogin(user.getLogin())).thenReturn(user);
        User foundUser = userServiceImpl.findUserByLogin(user.getLogin());
        assertNotNull(foundUser);
        assertEquals(foundUser,user);
      }
}