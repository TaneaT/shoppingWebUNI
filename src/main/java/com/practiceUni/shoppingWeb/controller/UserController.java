package com.practiceUni.shoppingWeb.controller;

import com.practiceUni.shoppingWeb.domain.User;
import com.practiceUni.shoppingWeb.service.UserService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/signup")
  public String showSignUpForm(User user) {
    return "add-user";
  }

  @PostMapping("/adduser")
  public String addUser(@Validated User user,BindingResult result,Model model){
    if(result.hasErrors()){
      return "add-user";
    }
    userService.createUser(user);
    return "redirect:/index";
  }

  @GetMapping("/update-form/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    User user = userService.findUserById(id);
           if(user == null) {
             throw  new IllegalArgumentException("Invalid user Id:" + id); }

    model.addAttribute("user", user);
    return "update-user";
  }

  @PostMapping("/update/{id}")
  public String updateUser(@PathVariable("id") Integer id, @Validated User user,
                           BindingResult result, Model model) {
    if (result.hasErrors()) {
      user.setId(id);
      return "update-user";
    }

    userService.createUser(user);
    return "redirect:/index";
  }

  @GetMapping("/delete/{id}")
  public String deleteUser(@PathVariable("id") Integer id, Model model) {
    User user = userService.findUserById(id);
            if(user == null){
              throw new IllegalArgumentException("Invalid user Id:" + id);}
    userService.deleteUserById(id);
    return "redirect:/index";
  }

  @GetMapping("/find/id/{id}")
  public User findUserById(@PathVariable Integer id) {
    return userService.findUserById(id);
  }

  @GetMapping("/find/login/{login}")
  public User findUserByLogin(@PathVariable String login) {
    return userService.findUserByLogin(login);
  }
}
