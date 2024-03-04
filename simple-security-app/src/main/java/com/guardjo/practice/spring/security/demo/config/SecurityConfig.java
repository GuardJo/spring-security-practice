package com.guardjo.practice.spring.security.demo.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(custom -> {
				custom.requestMatchers(PathRequest.toH2Console())
					.permitAll();
			})
			.csrf(AbstractHttpConfigurer::disable)
			.headers(custom -> custom.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

		return http.build();
	}
}
