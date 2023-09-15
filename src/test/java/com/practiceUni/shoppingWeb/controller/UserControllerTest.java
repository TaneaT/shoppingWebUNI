package com.practiceUni.shoppingWeb.controller;

import com.practiceUni.shoppingWeb.controller.UserController;
import com.practiceUni.shoppingWeb.domain.User;
import com.practiceUni.shoppingWeb.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private Model model;

    private UserController userController;

    @BeforeEach
    void setUp() {
        userController = new UserController(userService);
    }

    @Test
    void shouldShowSignUpForm() {
        String result = userController.showSignUpForm(new User());

        assertEquals("add-user", result);
    }

    @Test
    void shouldAddUserWithValidData() {
        User user = new User();

        Mockito.when(bindingResult.hasErrors()).thenReturn(false);

        String result = userController.addUser(user, bindingResult, model);

        assertEquals("redirect:/index", result);
        Mockito.verify(userService, Mockito.times(1)).createUser(user);
    }

    @Test
    void shouldNotAddUserWithInvalidData() {
        User user = new User();

        Mockito.when(bindingResult.hasErrors()).thenReturn(true);

        String result = userController.addUser(user, bindingResult, model);

        assertEquals("add-user", result);
        Mockito.verify(userService, Mockito.never()).createUser(any(User.class));
    }

    @Test
    void shouldShowUpdateForm() {
        Integer userId = 1;

        User user = new User();

        Mockito.when(userService.findUserById(userId)).thenReturn(user);

        String result = userController.showUpdateForm(userId, model);

        assertEquals("update-user", result);
        Mockito.verify(model, Mockito.times(1)).addAttribute("user", user);
    }

    @Test
    void shouldUpdateUserWithValidData() {
        Integer userId = 1;

        User user = new User();

        Mockito.when(bindingResult.hasErrors()).thenReturn(false);

        String result = userController.updateUser(userId, user, bindingResult, model);

        assertEquals("redirect:/index", result);
        Mockito.verify(userService, Mockito.times(1)).createUser(user);
    }

    @Test
    void shouldNotUpdateUserWithInvalidData() {
        Integer userId = 1;

        User user = new User();

        Mockito.when(bindingResult.hasErrors()).thenReturn(true);

        String result = userController.updateUser(userId, user, bindingResult, model);

        assertEquals("update-user", result);
        Mockito.verify(userService, Mockito.never()).createUser(any(User.class));
    }

    @Test
    void shouldDeleteUser() {
        Integer userId = 1;

        User user = new User();

        Mockito.when(userService.findUserById(userId)).thenReturn(user);
        Mockito.when(userService.deleteUserById(userId)).thenReturn(true);

        String result = userController.deleteUser(userId, model);

        assertEquals("redirect:/index", result);
        Mockito.verify(userService, Mockito.times(1)).deleteUserById(userId);
    }

    @Test
    void shouldFindUserById() {
        Integer userId = 1;

        User user = new User();

        Mockito.when(userService.findUserById(userId)).thenReturn(user);

        User foundUser = userController.findUserById(userId);

        assertNotNull(foundUser);
        assertEquals(user, foundUser);
    }

    @Test
    void shouldFindUserByLogin() {
        String login = "testLogin";

        User user = new User();

        Mockito.when(userService.findUserByLogin(login)).thenReturn(user);

        User foundUser = userController.findUserByLogin(login);

        assertNotNull(foundUser);
        assertEquals(user, foundUser);
    }
}
