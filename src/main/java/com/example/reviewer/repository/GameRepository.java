package com.example.reviewer.repository;

import com.example.reviewer.model.Game;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@jakarta.transaction.Transactional
@org.springframework.transaction.annotation.Transactional
public interface GameRepository extends JpaRepository<Game, Long> {
    public List<Game> findGamesByGenre(String genre);

}
