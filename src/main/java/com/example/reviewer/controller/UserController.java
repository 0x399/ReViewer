package com.example.reviewer.controller;

import com.example.reviewer.model.Game;
import com.example.reviewer.model.Review;
import com.example.reviewer.model.User;
import com.example.reviewer.service.GameService;
import com.example.reviewer.service.ReviewService;
import com.example.reviewer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ReviewService reviewService;

    @Autowired
    GameService gameService;

    public UserController(UserService userService, ReviewService reviewService, GameService gameService) {
        this.userService = userService;
        this.reviewService = reviewService;
        this.gameService = gameService;
    }

    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }
}
