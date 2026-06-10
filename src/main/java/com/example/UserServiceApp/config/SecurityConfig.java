package com.example.UserServiceApp.config;

import org.springframework.boot.security.autoconfigure.web.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF (Must be first for Postman and H2 'Connect' button)
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
//
                        .requestMatchers("/h2-console/**").permitAll()

                        //Allow API endpoint
                        .requestMatchers("/api/users/register",
                                "/api/users/email/**","/users/all","/api/users/delete/**","/api/users/update","/api/auth/me").permitAll()

                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()

                        //Temporary: Allow everything else to ensure nothing is blocked
                        .anyRequest().permitAll()
                )

                // Allow H2 to load its 'frames' inside the browser
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

        return http.build();
    }

    @Bean
    //This makes the encoder available for injection into your service layer
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
