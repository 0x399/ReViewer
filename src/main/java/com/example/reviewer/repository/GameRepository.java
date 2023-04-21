package com.example.reviewer.repository;

import com.example.reviewer.model.Game;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    public List<Game> findGamesByGenre(String genre);

}
