package com.blackjack.api;


import com.blackjack.persistence.repo.FavoriteStrategyRepository;
import com.blackjack.security.AuthUserPrincipal;
import com.blackjack.user.FavouriteStrategy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
public class FavouriteController {

    private final FavoriteStrategyRepository repo;

    public FavouriteController(FavoriteStrategyRepository repo) { this.repo = repo; }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody FavoriteDto dto) {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth != null && auth.getPrincipal() instanceof AuthUserPrincipal principal)) {
            return ResponseEntity.status(401).build();
        }
        FavouriteStrategy fs = new FavouriteStrategy(principal.getUserId(), dto.strategyName(), dto.configJson());
        repo.save(fs);
        return ResponseEntity.ok(fs);
    }

    @GetMapping
    public ResponseEntity<List<FavouriteStrategy>> list() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth != null && auth.getPrincipal() instanceof AuthUserPrincipal principal)) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(repo.findByUserId(principal.getUserId()));
    }

    public record FavoriteDto(String strategyName, String configJson) {}
}
