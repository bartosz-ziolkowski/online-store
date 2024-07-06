package com.bazi.online_store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configureSecurity(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorize) ->
            authorize
                .requestMatchers("/api/store/products", "/api/store/categories", "api/store/brands").permitAll()
                .requestMatchers("/api/store/account").authenticated())
                .httpBasic(Customizer.withDefaults()).formLogin(Customizer.withDefaults());

        return http.build();
    }
}
