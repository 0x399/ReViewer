package com.example.reviewer.controller;

import com.example.reviewer.model.Game;
import com.example.reviewer.model.Review;
import com.example.reviewer.model.ReviewDTO;
import com.example.reviewer.model.User;
import com.example.reviewer.service.GameService;
import com.example.reviewer.service.ReviewService;
import com.example.reviewer.service.UserService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@jakarta.transaction.Transactional
@org.springframework.transaction.annotation.Transactional
@Controller
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @Autowired
    UserService userService;

    @Autowired
    GameService gameService;

    @ModelAttribute(name = "reviewlist")
    public List<Review> reviewList(){
        return reviewService.getAll();
    }

    @ModelAttribute(name = "gamelist")
    public List<Game> gamelist(){
        return gameService.getAll();
    }

    @ModelAttribute(name = "userlist")
    public List<User> userlist(){
        return userService.getAll();
    }

    @ModelAttribute(name = "review")
    public Review review(){
        Review review = new Review();
        return review;
    }

    public ReviewController(ReviewService reviewService, UserService userService, GameService gameService) {
        this.reviewService = reviewService;
        this.userService = userService;
        this.gameService = gameService;
    }

    @GetMapping
    public String getAll(){
        return "reviews-list";
    }

    @GetMapping("/create-review")
    public String create(){
        return "create-review";
    }

    @PostMapping("/create-review")
    public String createNewReview(@RequestParam Game game,
                                  @RequestParam User user,
                                  @RequestParam String description,
                                  @RequestParam Byte score) throws IOException {
        Review review = new Review();
        review.setScore(score);
        review.setGame(game);
        review.setDescription(description);
        review.setUser(user);
        review.setCreatedAt(LocalDateTime.now());
        review.getGame().getReviews().add(review);
        review.getUser().getReviews().add(review);
        review.getGame().setAvgScore((review.getGame().getNumOfReviews() * review.getGame().getAvgScore() + review.getScore())
                / (review.getGame().getNumOfReviews() + 1));
        review.getGame().setNumOfReviews(review.getGame().getNumOfReviews() + 1);
        reviewService.createReview(review);
        return "redirect:/reviews";
    }

}
