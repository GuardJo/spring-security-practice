package com.guardjo.practice.spring.security.demo.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.guardjo.practice.spring.security.demo.config.auth.CustomUserDetails;
import com.guardjo.practice.spring.security.demo.domain.Product;
import com.guardjo.practice.spring.security.demo.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MainController {
	private final ProductService productService;

	@GetMapping("/main")
	public String main(@AuthenticationPrincipal CustomUserDetails principal, Model model) {
		log.info("GET /main");
		List<Product> productList = productService.findAll();
		model.addAttribute("username", principal.getUsername());
		model.addAttribute("products", productList);

		return "main.html";
	}
}
