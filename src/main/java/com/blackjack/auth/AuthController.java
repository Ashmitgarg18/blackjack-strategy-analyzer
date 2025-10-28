package com.blackjack.auth;

import com.blackjack.persistence.repo.UserRepository;
import com.blackjack.security.JwtUtil;
import com.blackjack.user.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository users;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository users, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest req) {
        if (users.existsByUsername(req.username())) {
            return ResponseEntity.badRequest().body(Map.of("error", "username_taken"));
        }
        User u = new User(req.username(), passwordEncoder.encode(req.password()), "USER");
        users.save(u);
        return ResponseEntity.ok(Map.of("username", u.getUsername()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest req) {
        var maybe = users.findByUsername(req.username());
        if (maybe.isEmpty()) return ResponseEntity.status(401).body(Map.of("error", "invalid_credentials"));
        var u = maybe.get();
        if (!passwordEncoder.matches(req.password(), u.getPasswordHash())) {
            return ResponseEntity.status(401).body(Map.of("error", "invalid_credentials"));
        }
        String token = jwtUtil.generateToken(u.getUsername(), u.getRole(), u.getId());
        return ResponseEntity.ok(Map.of("token", token, "username", u.getUsername(), "role", u.getRole()));
    }

    public record RegisterRequest(@jakarta.validation.constraints.NotBlank String username,
                                  @jakarta.validation.constraints.NotBlank String password) {}

    public record LoginRequest(@jakarta.validation.constraints.NotBlank String username,
                               @jakarta.validation.constraints.NotBlank String password) {}
}
