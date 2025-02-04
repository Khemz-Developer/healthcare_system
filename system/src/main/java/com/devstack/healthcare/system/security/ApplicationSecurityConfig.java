//package com.devstack.healthcare.system.security;
//
//import com.devstack.healthcare.system.jwt.JwtConfig;
//import com.devstack.healthcare.system.jwt.JwtTokenVerifier;
//import com.devstack.healthcare.system.jwt.JwtUsernamePasswordAuthenticationFilter;
//import com.devstack.healthcare.system.service.impl.ApplicationUserServiceImpl;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//
//
//import javax.crypto.SecretKey;
//import java.util.List;
//@Configuration
//@EnableWebSecurity
//public class ApplicationSecurityConfig{
//
//    private final PasswordEncoder passwordEncoder;
//    private final ApplicationUserServiceImpl applicationUserService;
//
//    //private final UserService userService;
//
//    private final JwtConfig jwtConfig;
//    private final SecretKey secretKey;
//
//    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, ApplicationUserServiceImpl applicationUserService, JwtConfig jwtConfig, SecretKey secretKey) {
//        this.passwordEncoder = passwordEncoder;
//        this.applicationUserService = applicationUserService;
//        this.jwtConfig = jwtConfig;
//        this.secretKey = secretKey;
//    }
//
//
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
//        corsConfiguration.setAllowedOrigins(List.of("*")); // Allow all origins
//        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allow all methods
//        corsConfiguration.setExposedHeaders(List.of("Authorization"));
//
//        http.csrf().disable()
//                .cors().configurationSource(request -> corsConfiguration)
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .addFilter(
//                        new JwtUsernamePasswordAuthenticationFilter(authenticationManager()
//                                ,jwtConfig,secretKey)
//                )
//                .addFilterAfter(new JwtTokenVerifier(jwtConfig, secretKey), JwtUsernamePasswordAuthenticationFilter.class)
//                .authorizeRequests()
//                .anyRequest()
//                .authenticated();
//
//        return http.build();
//    }
//}


//package com.devstack.healthcare.system.security;
//
//import com.devstack.healthcare.system.jwt.JwtConfig;
//import com.devstack.healthcare.system.jwt.JwtTokenVerifier;
//import com.devstack.healthcare.system.jwt.JwtUsernamePasswordAuthenticationFilter;
//import com.devstack.healthcare.system.service.impl.ApplicationUserServiceImpl;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.cors.CorsConfiguration;
//
//import javax.crypto.SecretKey;
//import java.util.List;
//
//@Configuration
//@EnableWebSecurity
//public class ApplicationSecurityConfig {
//
//    private final PasswordEncoder passwordEncoder;
//    private final ApplicationUserServiceImpl applicationUserService;
//    private final JwtConfig jwtConfig;
//    private final SecretKey secretKey;
//
//    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, ApplicationUserServiceImpl applicationUserService, JwtConfig jwtConfig, SecretKey secretKey) {
//        this.passwordEncoder = passwordEncoder;
//        this.applicationUserService = applicationUserService;
//        this.jwtConfig = jwtConfig;
//        this.secretKey = secretKey;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
//        corsConfiguration.setAllowedOrigins(List.of("*")); // Allow all origins
//        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allow all methods
//        corsConfiguration.setExposedHeaders(List.of("Authorization"));
//
//        http.csrf(csrf -> csrf.disable())
//                .cors(cors -> cors.configurationSource(request -> corsConfiguration))
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilter(new JwtUsernamePasswordAuthenticationFilter(authenticationManager(authenticationConfiguration), jwtConfig, secretKey))
//                .addFilterAfter(new JwtTokenVerifier(jwtConfig, secretKey), JwtUsernamePasswordAuthenticationFilter.class)
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/api/v1/user/register/**", "/api/v1/ambulance/**").permitAll()
//                        .anyRequest().authenticated());
//
//        return http.build();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//}



package com.devstack.healthcare.system.security;

import com.devstack.healthcare.system.jwt.JwtConfig;
import com.devstack.healthcare.system.jwt.JwtTokenVerifier;
import com.devstack.healthcare.system.jwt.JwtUsernamePasswordAuthenticationFilter;
import com.devstack.healthcare.system.service.impl.ApplicationUserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import javax.crypto.SecretKey;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserServiceImpl applicationUserService;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, ApplicationUserServiceImpl applicationUserService, JwtConfig jwtConfig, SecretKey secretKey) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserService = applicationUserService;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationConfiguration authenticationConfiguration) throws Exception {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        corsConfiguration.setAllowedOrigins(List.of("*")); // Allow all origins
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allow all methods
        corsConfiguration.setExposedHeaders(List.of("Authorization"));

        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(request -> corsConfiguration))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilter(new JwtUsernamePasswordAuthenticationFilter(authenticationManager(authenticationConfiguration), jwtConfig, secretKey))
                .addFilterAfter(new JwtTokenVerifier(jwtConfig, secretKey), JwtUsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/v1/user/register/**", "/api/v1/ambulance/**").permitAll()
                        .anyRequest().authenticated());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(applicationUserService);
        return daoAuthenticationProvider;
    }
}