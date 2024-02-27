package com.guardjo.practice.spring.security.basicsecurity.service;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 암호화를 하지 않는 일반 문자열 반환 PasswordEncoder 구현체
 */
public class PlainTextPasswordEncoder implements PasswordEncoder {
	@Override
	public String encode(CharSequence rawPassword) {
		return String.valueOf(rawPassword);
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return rawPassword.equals(encodedPassword);
	}
}
