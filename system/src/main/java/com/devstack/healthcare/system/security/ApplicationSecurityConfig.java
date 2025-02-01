package com.devstack.healthcare.system.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@EnableWebSecurity
public class ApplicationSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        corsConfiguration.setAllowedOrigins(List.of("*")); // Allow all origins
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allow all methods
        corsConfiguration.setExposedHeaders(List.of("Authorization"));

        http
                .cors(cors -> cors.configurationSource(request -> corsConfiguration))
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin.defaultSuccessUrl("/home", true));
        return http.build();
    }
}







//package com.devstack.healthcare.system.security;
//
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.web.cors.CorsConfiguration;
//
//import java.util.List;
//
//public class ApplicationSecurityConfig extends  WebSecurityConfigurerAdapter{
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowedHeaders(List.of("Authorization","Content-Type"));
//        corsConfiguration.setAllowedOrigins(List.of("*")); // Allow all origins
//        corsConfiguration.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS")); // Allow all methods
//        corsConfiguration.setExposedHeaders(List.of("Authorization"));
//    }
//}

