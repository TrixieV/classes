package com.app.classes.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain chain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((request) -> request
                .requestMatchers("/v1/courses").hasRole("MANAGER")
                .requestMatchers("/v1/teachers/**").hasAnyRole("MANAGER", "TEACHER")
                .requestMatchers("/v1/students/**").authenticated()
        );
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails trixie = User.builder()
                .username("trixie")
                .password("{noop}trix123")
                .roles("TEACHER")
                .build();

        UserDetails michelle = User.builder()
                .username("michelle")
                .password("{noop}mi123")
                .roles("TEACHER")
                .build();

        UserDetails tina = User.builder()
                .username("tina")
                .password("{noop}tin123")
                .roles("TEACHER", "MANAGER")
                .build();

        UserDetails max = User.builder()
                .username("max")
                .password("{noop}max123")
                .roles("STUDENT")
                .build();

        UserDetails henry = User.builder()
                .username("henry")
                .password("{noop}henry123")
                .roles("STUDENT")
                .build();

        return new InMemoryUserDetailsManager(trixie, michelle, tina, max, henry);
    }

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                        configurer
                                .anyRequest().authenticated())
                .logout(logout -> logout.permitAll());
        return http.build();
    }
}
