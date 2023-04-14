package com.example.reviewer.controller;

import com.example.reviewer.model.User;
import com.example.reviewer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public void getAll(){
        User user = new User();
        user.setFirstName("123");
        user.setLastName("456");
        user.setEmail("789");
        userService.createUser(user);
    }
}
