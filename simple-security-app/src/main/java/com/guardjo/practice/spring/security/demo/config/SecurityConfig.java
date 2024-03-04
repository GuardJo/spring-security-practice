package com.guardjo.practice.spring.security.demo.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.guardjo.practice.spring.security.demo.config.auth.CustomAuthProvider;
import com.guardjo.practice.spring.security.demo.config.auth.CustomUserDetails;
import com.guardjo.practice.spring.security.demo.domain.Account;
import com.guardjo.practice.spring.security.demo.service.AccountService;

@Configuration
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomAuthProvider customAuthProvider) throws Exception {
		http.authorizeHttpRequests(custom -> {
				custom.requestMatchers(PathRequest.toH2Console())
					.permitAll();
				custom.anyRequest().authenticated();
			})
			.csrf(AbstractHttpConfigurer::disable)
			.headers(custom -> custom.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
			.formLogin(custom -> {
				custom.defaultSuccessUrl("/main", true);
			})
			.authenticationProvider(customAuthProvider);

		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService(AccountService accountService) {
		return (username) -> {
			try {
				Account account = accountService.findAccount(username);
				return CustomUserDetails.create(account);
			} catch (Exception e) {
				throw new UsernameNotFoundException("Not Found Account");
			}
		};
	}

	@Bean
	public PasswordEncoder bcryptEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public PasswordEncoder scryptEncoder() {
		return SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8();
	}
}
