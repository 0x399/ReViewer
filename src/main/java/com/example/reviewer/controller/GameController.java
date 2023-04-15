package com.example.reviewer.controller;

import com.example.reviewer.model.Game;
import com.example.reviewer.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/games")
public class GameController {
    @Autowired
    GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public void getAll(){
        Game game = new Game();
        game.setName("name");
        game.setGenre("genre");
        gameService.createGame(game);
    }
}
