package com.practiceUni.shoppingWeb.controller;

import com.practiceUni.shoppingWeb.domain.Product;
import com.practiceUni.shoppingWeb.domain.User;
import com.practiceUni.shoppingWeb.service.BrandService;
import com.practiceUni.shoppingWeb.service.ProductService;
import com.practiceUni.shoppingWeb.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import javax.servlet.http.HttpSession;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

  @Mock private UserService userService;

  @Mock private BrandService brandService;

  @Mock private ProductService productService;

  @Mock private BindingResult bindingResult;

  @Mock private Model model;

  @Mock private RedirectAttributes redirectAttributes;

  private UserController userController;

  HttpSession session = mock(HttpSession.class);

  @BeforeEach
  void setUp() {
    userController = new UserController(userService, brandService, productService);
  }

  @Test
  void shouldShowSignUpForm() {
    String result = userController.showSignUpForm(model);

    assertEquals("add-user", result);
  }

  @Test
  void shouldSignUpWithValidData() {
    User user = new User();
    when(bindingResult.hasErrors()).thenReturn(false);

    String result = userController.signUp(user, bindingResult, model, session, redirectAttributes);

    assertEquals("redirect:/api/user/main", result);
    Mockito.verify(userService, Mockito.times(1)).createUser(user);
  }

  @Test
  void shouldNotSignUpWithInvalidData() {
    User user = new User();
    when(bindingResult.hasErrors()).thenReturn(true);

    String result = userController.signUp(user, bindingResult, model, null, redirectAttributes);

    assertEquals("add-user", result);
    Mockito.verify(userService, Mockito.never()).createUser(any(User.class));
  }

  @Test
  void shouldShowUpdateProfileForm() {
    Integer userId = 1;
    User user = new User();
    when(userService.findUserById(userId)).thenReturn(user);

    String result = userController.showUpdateProfileForm(userId, model);

    assertEquals("update", result);
    Mockito.verify(model, Mockito.times(1)).addAttribute("user", user);
  }

  @Test
  void shouldUpdatePasswordWithValidData() {
    Integer userId = 1;
    String oldPassword = "OldPassword";
    String newPassword = "NewPassword";
    User user = new User();
    user.setPassword(oldPassword);
    when(userService.findUserById(userId)).thenReturn(user);

    String result =
        userController.updatePassword(userId, oldPassword, newPassword, redirectAttributes);

    assertEquals("redirect:/api/user/profile", result);
    assertEquals(newPassword, user.getPassword());
    Mockito.verify(userService, Mockito.times(1)).updateUserPassword(user);
  }

  @Test
  void shouldNotUpdatePasswordWithInvalidOldPassword() {
    Integer userId = 1;
    String oldPassword = "WrongPassword";
    String newPassword = "NewPassword";
    User user = new User();
    user.setPassword("CorrectPassword"); // Set the correct old password
    when(userService.findUserById(userId)).thenReturn(user);

    String result =
        userController.updatePassword(userId, oldPassword, newPassword, redirectAttributes);

    assertEquals("redirect:/api/user/profile", result);
    assertNotEquals(newPassword, user.getPassword()); // Password should not have changed
    Mockito.verify(userService, Mockito.never()).updateUserPassword(user);
  }

  @Test
  void shouldDeleteAccount() {
    Integer userId = 1;
    User user = new User();
    Mockito.lenient().when(userService.findUserById(userId)).thenReturn(user);
    Mockito.lenient().when(userService.deleteUserById(userId)).thenReturn(true);

    String result = userController.deleteAccount(userId);

    assertEquals("redirect:/api/user/main", result);
    Mockito.verify(userService, Mockito.times(1)).deleteUserById(userId);
  }

  @Test
  void shouldRedirectToSignUpIfNotAuthenticated() {
    HttpSession session = new MockHttpSession();
    String result = userController.userProfile(model, session);

    assertEquals("redirect:/api/user/signup", result);
  }

  @Test
  void shouldRedirectToMainIfAuthenticated() {
    HttpSession session = new MockHttpSession();
    String login = "testLogin";
    session.setAttribute("userLogin", login);
    User user = new User();
    when(userService.findUserByLogin(login)).thenReturn(user);

    String result = userController.userProfile(model, session);

    assertEquals("profile", result);
    Mockito.verify(model, Mockito.times(1)).addAttribute("user", user);
  }

  @Test
  void shouldRedirectToMainIfPrincipalAuthenticated() {
    Principal principal = mock(Principal.class);
    String login = "testLogin";
    Mockito.lenient().when(principal.getName()).thenReturn(login);
    User user = new User();
    Mockito.lenient().when(userService.findUserByLogin(login)).thenReturn(user);

    String result = userController.mainPage(model, principal, login, session);

    assertEquals("main", result);
    Mockito.verify(model, Mockito.times(1)).addAttribute("user", user);
  }

  @Test
  void shouldRedirectToMainIfLoginNotNull() {
    HttpSession session = new MockHttpSession();
    String login = "testLogin";
    session.setAttribute("userLogin", login);
    User user = new User();
    when(userService.findUserByLogin(login)).thenReturn(user);

    String result = userController.mainPage(model, null, login, session);

    assertEquals("main", result);
    Mockito.verify(model, Mockito.times(1)).addAttribute("user", user);
  }

  @Test
  void shouldShowMainPage() {
    String login = "testLogin";
    HttpSession session = new MockHttpSession();
    session.setAttribute("userLogin", login);

    User user = new User();
    when(userService.findUserByLogin(login)).thenReturn(user);

    List<Product> menProducts = new ArrayList<>();
    List<Product> womenProducts = new ArrayList<>();
    List<Product> kidsProducts = new ArrayList<>();

    when(productService.findProductByCategory("men")).thenReturn(menProducts);
    when(productService.findProductByCategory("women")).thenReturn(womenProducts);
    when(productService.findProductByCategory("kids")).thenReturn(kidsProducts);

    String result = userController.mainPage(model, null, login, session);

    assertEquals("main", result);
    Mockito.verify(model, Mockito.times(1)).addAttribute("user", user);
    Mockito.verify(model, Mockito.times(1)).addAttribute("menProducts", menProducts);
    Mockito.verify(model, Mockito.times(1)).addAttribute("womenProducts", womenProducts);
    Mockito.verify(model, Mockito.times(1)).addAttribute("kidsProducts", kidsProducts);
  }

  @Test
  void shouldShowSignInForm() {
    String result = userController.showSignInForm(model);

    assertEquals("sign-in", result);
  }

  @Test
  void shouldSignInWithValidCredentials() {
    User user = new User();
    user.setLogin("testLogin");
    user.setPassword("testPassword");

    HttpSession session = new MockHttpSession();

    when(userService.authenticateUser("testLogin", "testPassword")).thenReturn(user);

    String result = userController.signIn(user, session, model);

    assertEquals("redirect:/api/user/main", result);
    assertEquals("testLogin", session.getAttribute("userLogin"));
    Mockito.verify(model, Mockito.times(1)).addAttribute("user", user);
  }

  @Test
  void shouldNotSignInWithInvalidCredentials() {
    User user = new User();
    user.setLogin("testLogin");
    user.setPassword("testPassword");

    when(userService.authenticateUser("testLogin", "testPassword")).thenReturn(null);

    String result = userController.signIn(user, null, model);

    assertEquals("sign-in", result);
    Mockito.verify(model, Mockito.times(1))
        .addAttribute("error", "Invalid login or password. Please try again.");
  }

  @Test
  void shouldLogout() {
    HttpSession session = new MockHttpSession();
    session.setAttribute("userLogin", "testLogin");

    String result = userController.logout(session);

    assertNull(session.getAttribute("userLogin"));
    assertEquals("redirect:/api/user/main", result);
  }
}
