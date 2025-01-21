package com.elegant.socialnetwork.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class AppConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(Authorize -> Authorize.requestMatchers("/api/**")
                .authenticated()
                .anyRequest()
                .permitAll())
                .httpBasic(withDefaults())
                .csrf(csrf -> csrf.disable());
        return http.build();
    }
}
