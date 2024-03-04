package com.guardjo.practice.spring.security.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.guardjo.practice.spring.security.demo.domain.Product;
import com.guardjo.practice.spring.security.demo.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
	private final ProductRepository productRepository;

	public List<Product> findAll() {
		return productRepository.findAll();
	}
}
