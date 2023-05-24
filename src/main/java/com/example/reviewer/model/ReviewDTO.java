package com.example.reviewer.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class ReviewDTO {
    private User user;

    private String description;

    private Long gameID;

    private String gameName;

    private Byte score;

    private LocalDateTime createdAt;

    public ReviewDTO(User user, String description, Long gameID, String gameName, Byte score) {
        this.user = user;
        this.description = description;
        this.gameID = gameID;
        this.gameName = gameName;
        this.score = score;
        this.createdAt = LocalDateTime.now();
    }
}
