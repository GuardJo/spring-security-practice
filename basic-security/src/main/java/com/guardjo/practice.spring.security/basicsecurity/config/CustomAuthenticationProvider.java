package com.guardjo.practice.spring.security.basicsecurity.config;

import java.util.List;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
	private final UserDetailsService userDetailsService; // Bean으로 등록된 InMemoryUserDetailsManager 주입
	private final PasswordEncoder passwordEncoder; // Bean으로 등록된 NoOpPasswordEncoder 주입

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = String.valueOf(authentication.getCredentials());

		UserDetails userDetails = userDetailsService.loadUserByUsername(username);

		if (passwordEncoder.matches(password, userDetails.getPassword())) {
			return new UsernamePasswordAuthenticationToken(username, password, List.of());
		} else {
			throw new AuthenticationCredentialsNotFoundException("Failed Authentication");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class
			.isAssignableFrom(authentication);
	}
}
