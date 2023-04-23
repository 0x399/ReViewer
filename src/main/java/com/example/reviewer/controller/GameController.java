package com.example.reviewer.controller;

import com.example.reviewer.model.Game;
import com.example.reviewer.model.Review;
import com.example.reviewer.model.User;
import com.example.reviewer.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @ModelAttribute(name = "game")
    public Game game(){
        Game game = new Game();
        return game;
    }

    public GameController(GameService gameService) {
        this.gameService = gameService;
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
    public String createNewGame(@ModelAttribute("game") Game game){
        gameService.createGame(game);
        return "redirect:/games";
    }

    @GetMapping("/{game_id}")
    public String read(@PathVariable("game_id") Long game_id, Model model){
        model.addAttribute("game", gameService.getById(game_id));
        return "game-info";
    }

    @GetMapping("/{game_id}/delete")
    public String delete(@PathVariable("game_id") Long game_id){
        gameService.deleteGame(gameService.getById(game_id));
        return "redirect:/games";
    }
}
