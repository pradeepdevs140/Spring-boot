package com.product.user_manage.JWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.product.user_manage.Entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    // Extract userId
    public Long extractUserId(String jwtToken) {
        String userIdStr = extractClaim(jwtToken, claims -> claims.get("userId", String.class));
        return userIdStr != null ? Long.parseLong(userIdStr) : null;
    }

    // Extract username
    public String extractUsername(String jwtToken) {
        return extractClaim(jwtToken, Claims::getSubject);
    }

    private <T> T extractClaim(String jwtToken, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(jwtToken);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String jwtToken) {
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();
    }

    private SecretKey getSignInKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    // Generate token for UserDetails
    public String generateToken(UserDetails user) {
        return generateToken(new HashMap<>(), (User) user);
    }

    // Generate token with extra claims
    public String generateToken(Map<String, Object> extraClaims, User user) {
        Map<String, Object> claims = new HashMap<>(extraClaims);
        claims.put("userId",  user.getId());

        return Jwts.builder()
                .claims(claims)
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSignInKey())
                .compact();
    }
    public boolean isTokenValid(String jwtToken , User user ) {
    	final Long userIdfromToken = extractUserId(jwtToken);
    	final Long userIdDetails = user.getId();
    	return (userIdfromToken!=null && userIdfromToken.equals(userIdDetails)
    			&& !isTokenExpired(jwtToken));
    }
    public boolean  isTokenExpired(String jwtToken) {
    	return extractExpiration(jwtToken ).before(new Date());
    }
    private Date extractExpiration(String jwtToken) {
    	  return extractClaim(jwtToken, Claims::getExpiration);
    }
}
