package com.example.reviewer.controller;

import com.example.reviewer.model.Game;
import com.example.reviewer.model.Review;
import com.example.reviewer.model.User;
import com.example.reviewer.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/games")
public class GameController {
    @Autowired
    GameService gameService;

    @ModelAttribute(name = "gamelist")
    public List<Game> gamelist(){
        return gameService.findGamesWithSorting();
    }

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public String getAll(){
        return "games-list";
    }

    @GetMapping("/{game_id}")
    public String read(@PathVariable("game_id") Long game_id, Model model){
        Review review = new Review();
        review.setGame(gameService.getById(game_id));
        review.setCreatedAt(LocalDateTime.now());
        review.setUser(new User());
        review.setDescription("dolbit normalno");
        review.setScore((byte) 5);
        Review review1 = review;
        gameService.getById(game_id).getReviews().add(review);
        gameService.getById(game_id).getReviews().add(review1);
        model.addAttribute("game", gameService.getById(game_id));
        return "game-info";
    }
}
