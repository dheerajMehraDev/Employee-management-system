package com.example.demo.Security.Configuration.Utilities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    @Value("${app.secret}")
    private String secretKey;

    @Value("${app.jwt.expiration:3600000}") // Default: 1 hour in milliseconds
    private long expiration;

    private Algorithm algorithm() {
        return Algorithm.HMAC256(secretKey);
    }

    /**
     * Extracts username from JWT token
     * @param token JWT token string
     * @return username from token subject
     * @throws RuntimeException if token is invalid
     */
    public String extractUsername(String token) {
        if (token == null || token.trim().isEmpty()) {
            throw new IllegalArgumentException("Token cannot be null or empty");
        }

        try {
            DecodedJWT decodedJWT = JWT.require(algorithm())
                    .build()
                    .verify(token);

            return decodedJWT.getSubject();

        } catch (JWTVerificationException ex) {
            throw new RuntimeException("Invalid JWT token", ex);
        }
    }

    /**
     * Validates JWT token against user details
     * @param token JWT token to validate
     * @param userDetails user details to validate against
     * @return true if token is valid, false otherwise
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        if (token == null || userDetails == null) {
            return false;
        }

        try {
            DecodedJWT decoded = JWT.require(algorithm())
                    .build()
                    .verify(token);

            String username = decoded.getSubject();
            Date expiresAt = decoded.getExpiresAt();

            return username != null
                    && username.equals(userDetails.getUsername())
                    && expiresAt != null
                    && expiresAt.after(new Date());

        } catch (JWTVerificationException ex) {
            return false;
        }
    }

    /**
     * Generates JWT token for user
     * @param userDetails user details to create token for
     * @return JWT token string
     */
    public String generateToken(UserDetails userDetails) {
        if (userDetails == null) {
            throw new IllegalArgumentException("UserDetails cannot be null");
        }

        Date now = new Date();
        Date exp = new Date(now.getTime() + expiration);

        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(auth -> auth.getAuthority())
                .collect(Collectors.toList());

        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withIssuedAt(now)
                .withExpiresAt(exp)
                .withClaim("roles", roles)
                .sign(algorithm());
    }

    /**
     * Extracts roles from JWT token
     * @param token JWT token string
     * @return list of role strings
     */
    public List<String> extractRoles(String token) {
        if (token == null || token.trim().isEmpty()) {
            throw new IllegalArgumentException("Token cannot be null or empty");
        }

        try {
            DecodedJWT decodedJWT = JWT.require(algorithm())
                    .build()
                    .verify(token);

            return decodedJWT.getClaim("roles").asList(String.class);

        } catch (JWTVerificationException ex) {
            throw new RuntimeException("Invalid JWT token", ex);
        }
    }
}