package com.guardjo.practice.spring.security.demo.config.auth;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomAuthProvider implements AuthenticationProvider {
	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		CustomUserDetails userDetails = (CustomUserDetails)userDetailsService.loadUserByUsername(username);

		validate(password, userDetails.getPassword());

		return new UsernamePasswordAuthenticationToken(userDetails, userDetails, userDetails.getAuthorities());
	}

	private void validate(String plain, String crypto) {
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
