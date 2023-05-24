package com.example.reviewer.controller;

import com.example.reviewer.model.Game;
import com.example.reviewer.model.Review;
import com.example.reviewer.model.User;
import com.example.reviewer.service.GameService;
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
}
