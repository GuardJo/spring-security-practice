package com.guardjo.practice.spring.security.basicsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {
	@GetMapping("/status")
	public String status() {
		return "OK";
	}
}
