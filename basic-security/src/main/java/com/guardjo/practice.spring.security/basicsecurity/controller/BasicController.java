package com.guardjo.practice.spring.security.basicsecurity.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class BasicController {
	@GetMapping("/status")
	public String status() {
		return "OK";
	}

	@GetMapping("/me")
	public String me(Authentication authentication) {

		return (String)authentication.getPrincipal();
	}

	@Async
	@GetMapping("/async-me")
	public void async() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		log.info("Thread : {}, me : {}", Thread.currentThread().getName(), authentication.getPrincipal());
	}
}
