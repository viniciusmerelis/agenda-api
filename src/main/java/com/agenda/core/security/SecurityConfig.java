package com.agenda.core.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Value("${jwt.auth.converter.resource-id}")
    private String resourceId;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
             .csrf().disable()
             .cors()
             .and()
             .authorizeHttpRequests()
             .anyRequest()
             .authenticated();
        http.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter());
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        var jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(this::extractRoles);
        return jwtAuthenticationConverter;
    }

    private Collection<GrantedAuthority> extractRoles(Jwt jwt) {
        Map<String, Object> resourceAccess;
        Map<String, Object> resource;
        Collection<String> roles;
        if (jwt.getClaim("resource_access") == null) {
            return Set.of();
        }
        resourceAccess = jwt.getClaim("resource_access");
        if (resourceAccess.get(resourceId) == null) {
            return Set.of();
        }
        resource = (Map<String, Object>) resourceAccess.get(resourceId);
        roles = (Collection<String>) resource.get("roles");
        return roles
             .stream()
             .map(SimpleGrantedAuthority::new)
             .collect(Collectors.toSet());
    }

}
