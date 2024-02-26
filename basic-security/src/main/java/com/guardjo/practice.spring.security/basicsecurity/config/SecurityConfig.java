package com.guardjo.practice.spring.security.basicsecurity.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.guardjo.practice.spring.security.basicsecurity.model.DummyUser;
import com.guardjo.practice.spring.security.basicsecurity.service.CustomInmemoryUserService;

@Configuration
public class SecurityConfig {
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
