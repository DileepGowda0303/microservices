package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
	    return httpSecurity
	        .authorizeExchange(exchanges -> exchanges
	            .pathMatchers(HttpMethod.GET, "/**").permitAll()  // ← Allow all GETs
	            .pathMatchers("/bank/accounts/**").authenticated()  // ← Stricter paths
	            .anyExchange().denyAll()  // Deny others (optional)
	        )
	        .csrf(csrf -> csrf.disable())
	        .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
	        .build();
	}


}
