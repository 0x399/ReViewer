package com.example.reviewer.service;

import com.example.reviewer.model.Game;
import com.example.reviewer.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService implements GameServiceInterface {
    @Autowired
    GameRepository gameRepository;

    @Override
    public List<Game> getAll() {
        return gameRepository.findAll();
    }

    public Game getById(Long id){
        return gameRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Game> getByGenre(String genre) {
        return gameRepository.findGamesByGenre(genre);
    }

    public List<Game> findGamesWithSorting(){return gameRepository.findAll(Sort.by(Sort.Direction.DESC,"avgScore"));}

    @Override
    public void createGame(Game game) {
        gameRepository.save(game);
    }

    @Override
    public void update(Game game) {
        Game gameFromDB = gameRepository.findById(game.getId()).orElseThrow();
        gameFromDB.setGenre(game.getGenre());
        gameFromDB.setName(game.getName());
        gameFromDB.setReviews(game.getReviews());
        gameRepository.save(gameFromDB);
    }

    @Override
    public void deleteGame(Game game) {
        gameRepository.delete(game);
    }
}
