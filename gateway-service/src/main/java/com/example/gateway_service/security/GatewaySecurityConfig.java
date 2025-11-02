package com.example.gateway_service.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.web.SecurityFilterChain;

import java.util.*;

@Configuration
public class GatewaySecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        JwtAuthenticationConverter jwtAuthConverter = new JwtAuthenticationConverter();
        jwtAuthConverter.setJwtGrantedAuthoritiesConverter(this::extractAuthorities);

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/actuator/**", "/eureka/**", "/fallback").permitAll()


                        //  PROPERTY SERVICE
                        .requestMatchers(HttpMethod.GET, "/api/property/**", "/api/unit/**")
                        .hasAnyRole("PROPERTY_READ", "PROPERTY_ADMIN", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/property/**", "/api/unit/**")
                        .hasAnyRole("PROPERTY_WRITE", "PROPERTY_ADMIN", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/property/**", "/api/unit/**")
                        .hasAnyRole("PROPERTY_WRITE", "PROPERTY_ADMIN", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/property/**", "/api/unit/**")
                        .hasAnyRole("PROPERTY_ADMIN", "ADMIN")


                        //  CONTRACT SERVICE
                        .requestMatchers(HttpMethod.GET, "/api/contract/**")
                        .hasAnyRole("CONTRACT_VIEW", "CONTRACT_ADMIN", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/contract/**")
                        .hasAnyRole("CONTRACT_CREATE", "CONTRACT_INITIATE", "CONTRACT_ADMIN", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/contract/**")
                        .hasAnyRole("CONTRACT_SIGN", "CONTRACT_ADMIN", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/contract/**")
                        .hasAnyRole("CONTRACT_ADMIN", "ADMIN")


                        //  PAYMENT SERVICE
                        .requestMatchers(HttpMethod.GET, "/api/payment/**")
                        .hasAnyRole("PAYMENT_READ", "PAYMENT_ADMIN", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/payment/**")
                        .hasAnyRole("PAYMENT_MAKE", "PAYMENT_ADMIN", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/payment/**")
                        .hasAnyRole("PAYMENT_ADMIN", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/payment/**")
                        .hasAnyRole("PAYMENT_ADMIN", "ADMIN")


                        //  NOTIFICATION SERVICE
                        .requestMatchers(HttpMethod.GET, "/api/notification/**")
                        .hasAnyRole("NOTIFICATION_RECEIVE", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/notification/**")
                        .hasAnyRole("NOTIFICATION_CREATE", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/notification/**")
                        .hasAnyRole("ADMIN")


                        //  USER MANAGEMENT SERVICE
                        .requestMatchers(HttpMethod.GET, "/api/users/**")
                        .hasAnyRole("USER_READ", "USER_ADMIN", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/users/**")
                        .hasAnyRole("USER_WRITE", "USER_ADMIN", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/users/**")
                        .hasAnyRole("USER_WRITE", "USER_ADMIN", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/users/**")
                        .hasAnyRole("USER_ADMIN", "ADMIN")

                        // أي Endpoint تاني لازم Auth
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter))
                );

        return http.build();
    }


    private Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
        Set<GrantedAuthority> authorities = new HashSet<>();

        // resource_access (client roles)
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        /*
        "gateway-client": {
      "roles": [
        "ADMIN"
      ]
    },
    "product-service": {
      "roles": [
        "PRODUCT_VIEW",
        "PRODUCT_EDIT"
      ]
    }
    map<String(ClientID),map<String("roles"),List<String>>>
         */
        if (resourceAccess != null) {
            for (Object value : resourceAccess.values()) {
                //value = map<String("roles"),List<String>>
                if (value instanceof Map<?, ?> map) {
                    Object rolesObj = map.get("roles");
                    //rolesObj = List<String>>
                    if (rolesObj instanceof Collection<?> roles) {
                        for (Object role : roles) {
                            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
                        }
                    }
                }
            }
        }

        // realm_access (realm roles)
        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
        if (realmAccess != null) {
            Object rolesObj = realmAccess.get("roles");
            if (rolesObj instanceof Collection<?> roles) {
                for (Object role : roles) {
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
                }
            }
        }
        return authorities;
    }
}
