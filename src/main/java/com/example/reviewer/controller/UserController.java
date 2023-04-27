package com.example.reviewer.controller;

import com.example.reviewer.model.Game;
import com.example.reviewer.model.Review;
import com.example.reviewer.model.User;
import com.example.reviewer.service.GameService;
import com.example.reviewer.service.ReviewService;
import com.example.reviewer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ReviewService reviewService;

    @Autowired
    GameService gameService;

    @ModelAttribute(name = "userlist")
    public List<User> userlist(){
        return userService.getAll();
    }

    @ModelAttribute(name = "user")
    public User user(){
        User user = new User();
        return user;
    }
    public UserController(UserService userService, ReviewService reviewService, GameService gameService) {
        this.userService = userService;
        this.reviewService = reviewService;
        this.gameService = gameService;
    }

    @GetMapping
    public String getAll(){
        return "users-list";
    }

    @GetMapping("/{user_id}")
    public String read(@PathVariable("user_id") Long user_id, Model model){
        model.addAttribute("user", userService.findById(user_id));
        return "user-info";
    }

    @GetMapping("/{user_id}/delete")
    public String delete(@PathVariable("user_id") Long user_id){
        userService.deleteUser(userService.findById(user_id));
        return "redirect:/users";
    }

    @GetMapping("/create_user")
    public String createNewGame(){
        return "create-user";
    }

    @PostMapping("/create_user")
    public String createNewGame(@ModelAttribute("user") User user){
        userService.createUser(user);
        return "redirect:/users";
    }
}
