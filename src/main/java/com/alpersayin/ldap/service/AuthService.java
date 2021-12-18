package com.alpersayin.ldap.service;

import com.alpersayin.ldap.exception.CustomRequestException;
import com.alpersayin.ldap.payload.LoginRequest;
import com.alpersayin.ldap.payload.LoginResponse;
import com.alpersayin.ldap.security.JwtProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.ZoneId;

@Slf4j
@Service
@AllArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    public LoginResponse login(LoginRequest loginRequest) {

        String token;
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtProvider.generateToken(authentication);
        } catch (Exception e) {
            throw new CustomRequestException("Error is occurred by validating user information.", e);
        }

        log.info("User logged in successfully with username : {}", loginRequest.getUsername());

        return LoginResponse.builder()
                .token(token)
                .expiresAt(jwtProvider.extractExpiration(token).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                .username(loginRequest.getUsername())
                .build();
    }

}
