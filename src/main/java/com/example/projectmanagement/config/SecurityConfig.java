

package com.example.projectmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // ✅ Disable CSRF for API use
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/api/projects/**").hasAnyRole("STUDENT", "SUPERVISOR", "COORDINATOR")
                        .requestMatchers("/api/reviews/**").hasAnyRole("STUDENT", "SUPERVISOR", "COORDINATOR")
                        .requestMatchers("/api/feedbacks/**").hasAnyRole("COORDINATOR", "SUPERVISOR", "STUDENT")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());  // ✅ Enables basic auth for Postman/Swagger
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails student = User.withUsername("student")
                .password("{noop}password")  // {noop} means no encoding
                .roles("STUDENT")
                .build();

        UserDetails supervisor = User.withUsername("supervisor")
                .password("{noop}password")
                .roles("SUPERVISOR")
                .build();

        UserDetails coordinator = User.withUsername("coordinator")
                .password("{noop}password")
                .roles("COORDINATOR")
                .build();

        return new InMemoryUserDetailsManager(student, supervisor, coordinator);
    }
}


