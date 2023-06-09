package com.example.reviewer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.Base64;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reviews")
public class Review {

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "review_user",
            joinColumns = @JoinColumn(name = "review_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private User user;

    private String description;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "review_game",
            joinColumns = @JoinColumn(name = "review_id"),
            inverseJoinColumns = @JoinColumn(name = "game_id")
    )
    private Game game;

    private LocalDateTime createdAt;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Byte score;

    @Override
    public String toString() {
        return "Review{" +
                "user=" + user.getFirstName() + " " + user.getLastName() +
                ", description='" + description + '\'' +
                ", game=" + game.getName() +
                ", createdAt=" + createdAt +
                ", id=" + id +
                ", score=" + score +
                '}';
    }
}
