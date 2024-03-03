package com.guardjo.practice.spring.security.basicsecurity.controller;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guardjo.practice.spring.security.basicsecurity.service.AuthenticateRunner;
import com.guardjo.practice.spring.security.basicsecurity.service.AuthenticationCaller;

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

	@GetMapping("/run")
	public void run() {
		log.info("Run : {}", Thread.currentThread().getName());

		Runnable task = new AuthenticateRunner();

		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService = new DelegatingSecurityContextExecutorService(executorService);

		executorService.execute(task);

	}

	@GetMapping("/call")
	public String call() throws ExecutionException, InterruptedException {
		log.info("Call : {}", Thread.currentThread().getName());

		Callable<String> task = new AuthenticationCaller();

		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService = new DelegatingSecurityContextExecutorService(executorService);

		return executorService.submit(task).get();
	}
}
