package com.guardjo.practice.spring.security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guardjo.practice.spring.security.demo.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
