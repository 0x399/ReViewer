package com.example.reviewer.controller;

import com.example.reviewer.model.Game;
import com.example.reviewer.model.Review;
import com.example.reviewer.model.User;
import com.example.reviewer.service.GameService;
import com.example.reviewer.service.ReviewService;
import com.example.reviewer.service.UserService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@jakarta.transaction.Transactional
@org.springframework.transaction.annotation.Transactional
@Controller
@RequestMapping("/games")
public class GameController {
    @Autowired
    GameService gameService;

    @Autowired
    ReviewService reviewService;

    @Autowired
    UserService userService;

    @ModelAttribute(name = "gamelist")
    public List<Game> gamelist(){
        return gameService.findGamesWithSorting();
    }

    @ModelAttribute(name = "userlist")
    public List<User> userlist(){
        return userService.getAll();
    }

    @ModelAttribute(name = "game")
    public Game game(){
        Game game = new Game();
        return game;
    }

    @ModelAttribute(name = "review")
    public Review review(){
        Review review = new Review();
        return review;
    }

    public GameController(GameService gameService, ReviewService reviewService, UserService userService) {
        this.gameService = gameService;
        this.reviewService = reviewService;
        this.userService = userService;
    }

    @GetMapping
    public String getAll(){
        return "games-list";
    }

    @GetMapping("/create_game")
    public String createNewGame(){
        return "create-game";
    }

    @PostMapping("/create_game")
    public String createNewGame(@RequestParam("name") String name,
                                @RequestParam("genre") String genre,
                                @RequestParam("image") MultipartFile image) throws IOException {
        Game game = new Game();
        game.setName(name);
        game.setGenre(genre);
        game.setImage(IOUtils.toByteArray(image.getInputStream()));
        gameService.createGame(game);
        return"redirect:/games";
    }

    @GetMapping("/game/{game_id}")
    public String read(@PathVariable("game_id") Long game_id, Model model){
        model.addAttribute("game", gameService.getById(game_id));
        return "game-info";
    }

    @GetMapping("/game/{game_id}/delete")
    public String delete(@PathVariable("game_id") Long game_id){
        gameService.deleteGame(gameService.getById(game_id));
        return "redirect:/games";
    }

    @GetMapping("/{genre}")
    public String getByGenre(@PathVariable("genre") String genre, Model model){
        model.addAttribute("gamelist", gameService.getByGenre(genre));
        return "games-list";
    }

    @PostMapping("/game/{game_id}")
    public String createNewReview(@PathVariable("game_id") Long gameId,
                                  @RequestParam User user,
                                  @RequestParam String description,
                                  @RequestParam Byte score) throws IOException {
        Review review = new Review();
        review.setScore(score);
        review.setGame(gameService.getById(gameId));
        review.setDescription(description);
        review.setUser(user);
        review.setCreatedAt(LocalDateTime.now());
        review.getGame().getReviews().add(review);
        review.getUser().getReviews().add(review);
        review.getGame().setAvgScore((review.getGame().getNumOfReviews() * review.getGame().getAvgScore() + review.getScore())
                / (review.getGame().getNumOfReviews() + 1));
        review.getGame().setNumOfReviews(review.getGame().getNumOfReviews() + 1);
        reviewService.createReview(review);
        return "redirect:/games/game/" + gameId;
    }
}
