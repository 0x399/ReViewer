package com.example.reviewer.repository;

import com.example.reviewer.model.Game;
import com.example.reviewer.model.Review;
import com.example.reviewer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    public List<Review> findByUser_Id(Long userId);

    public List<Review> findByGame_Id(Long gameId);
}
