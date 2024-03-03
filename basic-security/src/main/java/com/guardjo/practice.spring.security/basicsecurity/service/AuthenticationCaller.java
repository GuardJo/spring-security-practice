package com.guardjo.practice.spring.security.basicsecurity.service;

import java.util.concurrent.Callable;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthenticationCaller implements Callable<String> {
	@Override
	public String call() throws Exception {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();

		log.info("Call : {}, me : {}", Thread.currentThread().getName(), authentication.getPrincipal());

		return authentication.getPrincipal().toString();
	}
}
