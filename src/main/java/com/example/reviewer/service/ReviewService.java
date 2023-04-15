package com.example.reviewer.service;

import com.example.reviewer.model.Game;
import com.example.reviewer.model.Review;
import com.example.reviewer.model.User;
import com.example.reviewer.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService implements ReviewServiceInterface{

    @Autowired
    ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void createReview(Review review) {
        reviewRepository.save(review);
    }

    @Override
    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    @Override
    public List<Review> getReviewsByGame(Game game) {
        return reviewRepository.findByGame_Id(game.getId());
    }

    @Override
    public List<Review> getReviewsByUser(User user) {
        return reviewRepository.findByUser_Id(user.getId());
    }

    @Override
    public void update(Review review) {
        Review reviewFromDB = reviewRepository.findById(review.getId()).orElseThrow();
        reviewFromDB.setGame(review.getGame());
        reviewFromDB.setUser(review.getUser());
        reviewFromDB.setDescription(review.getDescription());
        reviewFromDB.setCreatedAt(LocalDateTime.now());
        reviewRepository.save(reviewFromDB);
    }

    @Override
    public void deleteReview(Review review) {
        reviewRepository.delete(review);
    }
}
