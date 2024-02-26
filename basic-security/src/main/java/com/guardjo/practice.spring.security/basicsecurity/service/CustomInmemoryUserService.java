package com.guardjo.practice.spring.security.basicsecurity.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Deprecated
public class CustomInmemoryUserService implements UserDetailsService {
	private final List<UserDetails> users;

	public CustomInmemoryUserService(List<UserDetails> users) {
		this.users = users;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return users.stream()
			.filter(userDetails -> userDetails.getUsername().equals(username))
			.findFirst()
			.orElseThrow(() -> new UsernameNotFoundException("Not Found Username " + username));
	}
}
