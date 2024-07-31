package com.bazi.online_store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import java.util.Collections;
import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configureSecurity(HttpSecurity http) throws Exception {

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConvert());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(corsCustomizer -> corsCustomizer.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                    config.setAllowedMethods(Collections.singletonList("*"));
                    config.setAllowCredentials(true);
                    config.setAllowedHeaders(Collections.singletonList("*"));
                    config.setExposedHeaders(List.of("Authorization"));
                    config.setMaxAge(3600L);
                    return config;
                }))
                .csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests((authorize) ->
                authorize
                        .requestMatchers("/api/store/categories","/api/store/brands","/api/store/products", "/api/deliverymethods", "/basket", "/basket/**", "/api/payments/", "/api/payments/**", "/api/store/products/**")
                        .permitAll()
                        .requestMatchers("/api/orders").hasRole("ADMIN"))
                        .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtCustomizer ->
                            jwtCustomizer.jwtAuthenticationConverter(jwtAuthenticationConverter)));
                        // .httpBasic(Customizer.withDefaults())
                        //.formLogin(Customizer.withDefaults());
        return http.build();
    }
}
