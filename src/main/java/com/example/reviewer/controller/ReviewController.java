package com.example.reviewer.controller;

import com.example.reviewer.model.Game;
import com.example.reviewer.model.Review;
import com.example.reviewer.service.GameService;
import com.example.reviewer.service.ReviewService;
import com.example.reviewer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @Autowired
    UserService userService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public void getAll(){
        Review game = new Review();
        game.setUser(userService.findById(13L));
        game.setDescription("obama");
        game.setCreatedAt(LocalDateTime.now());
        reviewService.createReview(game);
    }
}
