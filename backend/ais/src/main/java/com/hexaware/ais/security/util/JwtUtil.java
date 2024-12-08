package com.hexaware.ais.security.util;

import java.util.Date;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;


/*
 * @Author: Kishlay Kumar
 * Class: JwtUtil
 * Description: This class is used to generate, extract and validate JWT tokens.
 */
@Component
public class JwtUtil {

    private static final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

    private final String SECRET_KEY = dotenv.get("JWT_SECRET_KEY");
    private final long EXPIRATION_TIME = Long.parseLong(dotenv.get("JWT_EXPIRATION_TIME", "36000000")); // Default: 10 hours

    public String generateToken(String username, String role) {

        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {

        try {

            Jwts.parserBuilder().setSigningKey(SECRET_KEY.getBytes()).build().parseClaimsJws(token);
            return true;
        }
        catch (JwtException | IllegalArgumentException e) {

            return false;
        }
    }

    public boolean validateToken(String token, String username) {

        try {

            String extractedUsername = extractUsername(token);
            return extractedUsername.equals(username) && !isTokenExpired(token);
        }
        catch (JwtException | IllegalArgumentException e) {

            return false;
        }
    }

    private boolean isTokenExpired(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }

    public String refreshToken(String token) {

        String username = extractUsername(token);

        if (!validateToken(token)) {

            throw new JwtException("Token is invalid or expired");
        }

        String role = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
        return generateToken(username, role);
    }
}