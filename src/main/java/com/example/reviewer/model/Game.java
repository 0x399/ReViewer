package com.example.reviewer.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.util.List;

@Data
@AllArgsConstructor
@Table(name = "games")
@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "game")
    private List<Review> reviews;

    @Column(name = "genre", nullable = false)
    private String genre;

    @Column(name = "numofreviews")
    private int numOfReviews;

    @Column(name = "avgscore", columnDefinition = "DOUBLE PRECISION")
    private double avgScore;

    public Game() {
        this.setNumOfReviews(0);
    }
}
