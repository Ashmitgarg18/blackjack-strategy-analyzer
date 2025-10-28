package com.blackjack.user;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "favorite_strategy")
@Data
public class FavouriteStrategy {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String strategyName;

    @Column(columnDefinition = "TEXT")
    private String configJson; // store DTO or params as JSON

    public FavouriteStrategy(Long userId, String strategyName, String configJson) {
        this.userId = userId;
        this.strategyName = strategyName;
        this.configJson = configJson;
    }
}
