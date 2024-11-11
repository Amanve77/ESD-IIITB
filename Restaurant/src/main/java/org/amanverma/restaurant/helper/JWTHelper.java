package org.amanverma.restaurant.helper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.function.Function;

@Component
public class JWTHelper {

    // Secret key with HS512 signature algorithm (512-bit key)
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    // Creates a JWT token using the username and optional additional claims
    public String createToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims) // You can add any claims you want here
                .setSubject(username) // Set the username as the subject
                .setIssuedAt(new Date()) // Set issued date
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // Set expiration time (1 hour)
                .signWith(SECRET_KEY) // Sign with the SECRET_KEY
                .compact();
    }

    // Extract the username from the token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extract expiration date from the token
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Generic method to extract any claim from the JWT
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Parse all claims from the token
    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new RuntimeException("Invalid JWT token", e);
        }
    }

    // Check if the token has expired
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Generate a token (using username only)
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    // Validate the token by checking username and expiration
    public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

}
