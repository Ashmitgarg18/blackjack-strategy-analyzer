package com.blackjack.persistence.repo;

import com.blackjack.user.FavouriteStrategy;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FavoriteStrategyRepository extends JpaRepository<FavouriteStrategy, Long> {
    List<FavouriteStrategy> findByUserId(Long userId);
}

