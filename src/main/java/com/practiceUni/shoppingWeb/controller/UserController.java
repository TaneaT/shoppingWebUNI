package com.practiceUni.shoppingWeb.controller;

import com.practiceUni.shoppingWeb.domain.Brand;
import com.practiceUni.shoppingWeb.domain.Product;
import com.practiceUni.shoppingWeb.domain.User;
import com.practiceUni.shoppingWeb.service.BrandService;
import com.practiceUni.shoppingWeb.service.ProductService;
import com.practiceUni.shoppingWeb.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("api/user")
public class UserController {

  private final UserService userService;

  private final BrandService brandService;

  private final ProductService productService;

  public UserController(UserService userService, BrandService brandService, ProductService productService) {
    this.userService = userService;
    this.brandService = brandService;
    this.productService = productService;
  }

  @GetMapping("/main")
  public String mainPage(Model model, Principal principal, @RequestParam(value = "login", required = false) String login, HttpSession session) {
    User user = null;

    if (login != null) {
      user = userService.findUserByLogin(login);
      session.setAttribute("userLogin", login);
    } else if (principal != null) {
      login = principal.getName();
      user = userService.findUserByLogin(login);
      session.setAttribute("userLogin", login);
    } else {
      login = (String) session.getAttribute("userLogin");
      if (login != null) {
        user = userService.findUserByLogin(login);
      }
    }


    model.addAttribute("user", user);
    model.addAttribute("menProducts", productService.findProductByCategory("men"));
    model.addAttribute("womenProducts", productService.findProductByCategory("women"));
    model.addAttribute("kidsProducts", productService.findProductByCategory("kids"));

    return "main";
  }




  @GetMapping("/signup")
  public String showSignUpForm(Model model) {
    model.addAttribute("user", new User());
    return "add-user";
  }

  @PostMapping("/adduser")
  public String signUp(@ModelAttribute("user") User user, BindingResult bindingResult, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
    if (bindingResult.hasErrors()) {
      return "add-user";
    }

    try {
      userService.createUser(user);
      System.out.println("User created successfully");

      // Set the user's login as a session attribute
      session.setAttribute("userLogin", user.getLogin());

      redirectAttributes.addAttribute("login", user.getLogin());

      return "redirect:/api/user/main"; // Redirect to the main page
    } catch (Exception e) {
      e.printStackTrace();
      model.addAttribute("error", "An error occurred while creating the user.");
      System.out.println("Error creating user: " + e.getMessage());
      return "add-user";
    }
  }


  @GetMapping("/profile")
  public String userProfile(Model model, HttpSession session) {
    String login = (String) session.getAttribute("userLogin");

    if (login != null) {
      User user = userService.findUserByLogin(login);

      if (user != null) {
        model.addAttribute("user", user);

        // Retrieve brands and add them to the model
        List<Brand> brands = brandService.getAllBrands();
        model.addAttribute("brands", brands);

        return "profile";
      }
    }

    // If the user is not authenticated or the user is not found, redirect to sign up
    return "redirect:/api/user/signup";
  }



  @GetMapping("/logout")
  public String logout(HttpSession session) {
    session.removeAttribute("userLogin");
    return "redirect:/api/user/main";
  }

  @GetMapping("/signin")
  public String showSignInForm(Model model) {
    model.addAttribute("user", new User());
    return "sign-in";
  }

  @PostMapping("/signin")
  public String signIn(@ModelAttribute("user") User user, HttpSession session, Model model) {
    User authenticatedUser = userService.authenticateUser(user.getLogin(), user.getPassword());

    if (authenticatedUser != null) {
      session.setAttribute("userLogin", authenticatedUser.getLogin());
      model.addAttribute("user", authenticatedUser);
      return "redirect:/api/user/main";
    } else {
      model.addAttribute("error", "Invalid login or password. Please try again.");
      return "sign-in";
    }
  }


  @GetMapping("/profile/update/{id}")
  public String showUpdateProfileForm(@PathVariable Integer id, Model model) {
    // Retrieve user information by id and pass it to the view
    User user = userService.findUserById(id);
    model.addAttribute("user", user);
    return "update";
  }

  @PostMapping("/profile/update/address")
  public String updateAddress(@RequestParam("userId") Integer userId, @RequestParam("newAddress") String newAddress, RedirectAttributes redirectAttributes) {
    User user = userService.findUserById(userId);
    user.setAddress(newAddress);
    userService.updateUser(user);
    redirectAttributes.addFlashAttribute("message", "Address updated successfully");
    return "redirect:/api/user/profile";
  }

  @PostMapping("/profile/update/password")
  public String updatePassword(@RequestParam("userId") Integer userId, @RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, RedirectAttributes redirectAttributes) {
    User user = userService.findUserById(userId);
    if (oldPassword.equals(user.getPassword())) {
      user.setPassword(newPassword);
      userService.updateUserPassword(user);
      redirectAttributes.addFlashAttribute("message", "Password updated successfully");
    } else {
      redirectAttributes.addFlashAttribute("message", "Incorrect old password. Password not updated.");
    }
    return "redirect:/api/user/profile";
  }

  @PostMapping("profile/delete")
  public String deleteAccount(@RequestParam("userId") Integer userId) {
    userService.deleteUserById(userId);

    return "redirect:/api/user/main";
  }

}





