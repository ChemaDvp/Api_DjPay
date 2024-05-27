package com.djpay.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ApiApplication {

	public static void main(String[] args) {SpringApplication.run(ApiApplication.class, args);}

	/*
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf(csrf -> csrf.disable()) // Deshabilitar CSRF
				.authorizeHttpRequests(authz -> authz
						.anyRequest().permitAll() // Permitir todas las solicitudes
				);
		return http.build();
	}
	*/
}
