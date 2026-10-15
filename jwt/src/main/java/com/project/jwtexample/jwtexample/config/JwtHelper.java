package com.project.jwtexample.jwtexample.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtHelper {

    // Token validity = 5 hours
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 1000L;

    private final String secret =
            "afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf";


    // Create signing key
    private SecretKey getSigningKey() {

        return Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8)
        );
    }


    // Get username from JWT
    public String getUsernameFromToken(String token) {

        return getClaimFromToken(
                token,
                Claims::getSubject
        );
    }


    // Get expiration date from JWT
    public Date getExpirationDateFromToken(String token) {

        return getClaimFromToken(
                token,
                Claims::getExpiration
        );
    }


    // Get any claim from JWT
    public <T> T getClaimFromToken(
            String token,
            Function<Claims, T> claimsResolver) {

        final Claims claims =
                getAllClaimsFromToken(token);

        return claimsResolver.apply(claims);
    }


    // Read and verify JWT
    private Claims getAllClaimsFromToken(String token) {

        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    // Check whether token has expired
    private Boolean isTokenExpired(String token) {

        final Date expiration =
                getExpirationDateFromToken(token);

        return expiration.before(new Date());
    }


    // Generate JWT token
    public String generateToken(UserDetails userDetails) {

        Map<String, Object> claims =
                new HashMap<>();

        return doGenerateToken(
                claims,
                userDetails.getUsername()
        );
    }


    // Create JWT
    private String doGenerateToken(
            Map<String, Object> claims,
            String subject) {

        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + JWT_TOKEN_VALIDITY
                        )
                )
                .signWith(getSigningKey())
                .compact();
    }


    // Validate JWT
    public Boolean validateToken(
            String token,
            UserDetails userDetails) {

        final String username =
                getUsernameFromToken(token);

        return username.equals(
                userDetails.getUsername()
        )
                && !isTokenExpired(token);
    }
}