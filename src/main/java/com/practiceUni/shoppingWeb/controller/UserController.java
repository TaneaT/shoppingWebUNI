package com.practiceUni.shoppingWeb.controller;

import com.practiceUni.shoppingWeb.domain.User;
import com.practiceUni.shoppingWeb.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteUserById (@PathVariable Integer id){
        return userService.deleteUserById(id);
    }

    @GetMapping("/find/id/{id}")
    public User findUserById(@PathVariable Integer id){
        return userService.findUserById(id);
    }

    @GetMapping("/find/login/{login}")
    public User findUserByLogin(@PathVariable String login){
        return userService.findUserByLogin(login);
    }

}
