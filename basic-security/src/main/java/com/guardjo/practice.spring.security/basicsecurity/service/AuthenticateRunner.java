package com.guardjo.practice.spring.security.basicsecurity.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticateRunner implements Runnable {
	@Override
	public void run() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();

		log.info("Run : {}, me : {}", Thread.currentThread().getName(), authentication.getPrincipal());
	}
}
