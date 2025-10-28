package com.blackjack.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final JwtProperties props;
    private final Key key;

    public JwtUtil(JwtProperties props) {
        this.props = props;
        byte[] keyBytes = Decoders.BASE64.decode(props.getSecret());
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String username, String role, long userId) {
        long now = System.currentTimeMillis();
        Date exp = new Date(now + props.getExpirationSeconds() * 1000L);
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .claim("uid", userId)
                .setIssuedAt(new Date(now))
                .setExpiration(exp)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Jws<Claims> parse(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
    }
}
