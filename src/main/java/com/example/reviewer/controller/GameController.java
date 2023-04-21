package com.example.reviewer.controller;

import com.example.reviewer.model.Game;
import com.example.reviewer.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
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
}
