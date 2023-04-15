package com.example.reviewer.service;

import com.example.reviewer.model.Game;

import java.util.List;

public interface GameServiceInterface {
    public List<Game> getAll();
    public List<Game> getByGenre(String genre);
    public void createGame(Game game);
    public void update(Game game);
    public void deleteGame(Game game);
}
