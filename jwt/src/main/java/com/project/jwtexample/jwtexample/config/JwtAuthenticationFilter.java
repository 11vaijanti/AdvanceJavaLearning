package com.project.jwtexample.jwtexample.config;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger =
            LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String requestHeader = request.getHeader("Authorization");

        logger.info("Authorization Header: {}", requestHeader);

        String username = null;
        String token = null;

        // Check Authorization header
        if (requestHeader != null &&
                requestHeader.startsWith("Bearer ")) {

            token = requestHeader.substring(7);

            try {

                username = jwtHelper.getUsernameFromToken(token);

                logger.info("Username extracted from JWT: {}", username);

            } catch (IllegalArgumentException e) {

                logger.error("Illegal argument while fetching username", e);

            } catch (ExpiredJwtException e) {

                logger.error("JWT token is expired", e);

            } catch (MalformedJwtException e) {

                logger.error("Invalid JWT token", e);

            } catch (Exception e) {

                logger.error("Error while processing JWT", e);
            }

        } else {

            logger.info("No Bearer token found");
        }

        // Authenticate user
        if (username != null &&
                SecurityContextHolder
                        .getContext()
                        .getAuthentication() == null) {

            UserDetails userDetails =
                    userDetailsService
                            .loadUserByUsername(username);

            logger.info(
                    "User authorities: {}",
                    userDetails.getAuthorities()
            );

            boolean validToken =
                    jwtHelper.validateToken(
                            token,
                            userDetails
                    );

            logger.info("Token valid: {}", validToken);

            if (validToken) {

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authentication.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authentication);

                logger.info(
                        "User authenticated successfully: {}",
                        username
                );
            }
        }

        logger.info(
                "Authentication: {}",
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
        );

        // Continue request
        filterChain.doFilter(request, response);
    }
}