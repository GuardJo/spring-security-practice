package com.guardjo.practice.spring.security.basicsecurity.config;

import java.util.List;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.guardjo.practice.spring.security.basicsecurity.model.DummyUser;
import com.guardjo.practice.spring.security.basicsecurity.service.CustomInmemoryUserService;

@Configuration
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(registry -> {
				registry.requestMatchers(PathRequest.toH2Console()).permitAll()
					.anyRequest().authenticated()
					.requestMatchers("/errors").permitAll();
			})
			.csrf(AbstractHttpConfigurer::disable)
			.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
			.httpBasic(httpSecurityHttpBasicConfigurer -> httpSecurityHttpBasicConfigurer.init(http));
		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		var user = new DummyUser();

		return new CustomInmemoryUserService(List.of(user));
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
