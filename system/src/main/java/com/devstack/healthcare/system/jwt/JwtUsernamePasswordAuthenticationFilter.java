package com.devstack.healthcare.system.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class JwtUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtConfig jwtConfig;

    private final SecretKey secretKey;

    public JwtUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager, JwtConfig jwtConfig, SecretKey secretKey) {
        this.authenticationManager = authenticationManager;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }




    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
           UsernamePasswordAuthenticationRequest usernamePasswordAuthenticationRequest =new
                   ObjectMapper().readValue(request.getInputStream(), UsernamePasswordAuthenticationRequest.class);

              Authentication authentication = new UsernamePasswordAuthenticationToken(
                     usernamePasswordAuthenticationRequest.getUsername(),
                     usernamePasswordAuthenticationRequest.getPassword()
              );

                Authentication authenticate = authenticationManager.authenticate(authentication);
                return authenticate;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtConfig.getTokenExpirationAfterDays())))
                .signWith(secretKey)
                .compact();

        response.addHeader(jwtConfig.getAuthorizationHeader(), jwtConfig.getTokenPrefix() + token);
    }

}
