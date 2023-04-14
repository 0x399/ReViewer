package com.example.reviewer.service;

import com.example.reviewer.model.Game;
import com.example.reviewer.model.Review;
import com.example.reviewer.model.User;

import java.util.List;

public interface ReviewServiceInterface {
    public void createReview(Review review);
    public List<Review> getAll();
    public List<Review> getReviewsByGame(Game game);
    public List<Review> getReviewsByUser(User user);
    public void update(Review review);
    public void deleteReview(Review review);
}
