package com.guardjo.practice.spring.security.demo.config.auth;

import java.util.Objects;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.guardjo.practice.spring.security.demo.domain.types.AlgorithmType;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomAuthProvider implements AuthenticationProvider {
	private final UserDetailsService userDetailsService;
	private final PasswordEncoder bcryptEncoder;
	private final PasswordEncoder scryptEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		CustomUserDetails userDetails = (CustomUserDetails)userDetailsService.loadUserByUsername(username);

		if (Objects.requireNonNull(userDetails.getAccount().getAlgorithm()) == AlgorithmType.scrypt) {
			validate(password, userDetails.getPassword(), scryptEncoder);
		} else {
			validate(password, userDetails.getPassword(), bcryptEncoder);
		}

		return new UsernamePasswordAuthenticationToken(userDetails, userDetails, userDetails.getAuthorities());
	}

	private void validate(String plain, String crypto, PasswordEncoder passwordEncoder) {
		if (passwordEncoder.matches(plain, crypto)) {
		} else {
			throw new BadCredentialsException("Wrong Password");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
