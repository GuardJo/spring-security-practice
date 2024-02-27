package com.guardjo.practice.spring.security.basicsecurity.config;

import java.security.NoSuchAlgorithmException;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(registry -> {
				registry.requestMatchers(PathRequest.toH2Console()).permitAll()
					.anyRequest().authenticated();
			})
			.csrf(AbstractHttpConfigurer::disable)
			.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
			.httpBasic(httpSecurityHttpBasicConfigurer -> httpSecurityHttpBasicConfigurer.init(http));
		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService(DataSource dataSource) {
		String userByUsernameQuery = "select u.username, u.password, u.enabled from dummy_user u where u.username = ?";
		String authsByUserQuery = "select a.username, a.authority from dummy_authority a where a.username = ?";

		var userDetailsService = new JdbcUserDetailsManager(dataSource);

		userDetailsService.setUsersByUsernameQuery(userByUsernameQuery);
		userDetailsService.setAuthoritiesByUsernameQuery(authsByUserQuery);

		return userDetailsService;
	}

	@Bean
	public PasswordEncoder passwordEncoder() throws NoSuchAlgorithmException {
		int cpuCost = 16384;
		int memoryCost = 8;
		int parallelization = 1;
		int keyLength = 32;
		int saltLength = 64;

		return new SCryptPasswordEncoder(cpuCost, memoryCost, parallelization, keyLength, saltLength);
	}
}
