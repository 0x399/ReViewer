package com.example.reviewer.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import java.util.Base64;
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

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
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

    @Lob
    @Column(name = "image", columnDefinition = "bytea")
    private byte[] image;

    @Override
    public String toString() {
        return
                " Name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", numOfReviews=" + numOfReviews +
                ", avgScore=" + avgScore +
                '}';
    }


    public String getImageAsText(){
        return Base64.getEncoder().encodeToString(image);
    }
}
