package com.social.media.application.controller;

import com.social.media.application.model.User;
import com.social.media.application.model.UserModel;
import com.social.media.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home()
    {
        return "this is home";
    }

    // Post mapping for adding a new user.
    @PostMapping("/signup")
    public long addUser(@RequestBody User user)
    {
        return userService.addUser(user);
    }

    // Get mapping for validating a user.
    @GetMapping("/login")
    public boolean login(@RequestBody UserModel userModel){
        return userService.login(userModel);
    }

}
