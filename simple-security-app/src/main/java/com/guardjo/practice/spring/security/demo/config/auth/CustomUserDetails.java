package com.guardjo.practice.spring.security.demo.config.auth;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.guardjo.practice.spring.security.demo.domain.Account;

import lombok.Getter;

@Getter
public class CustomUserDetails implements UserDetails {
	private final Account account;

	private CustomUserDetails(Account account) {
		this.account = account;
	}

	public static CustomUserDetails create(Account account) {
		return new CustomUserDetails(account);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return account.getAuthorities().stream()
			.map(a -> new SimpleGrantedAuthority(a.getName()))
			.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		String password = account.getPassword();

		return switch (account.getAlgorithm()) {
			case scrypt -> "{scrypt}" + password;
			default -> "{bcrypt}" + password;
		};
	}

	@Override
	public String getUsername() {
		return account.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
