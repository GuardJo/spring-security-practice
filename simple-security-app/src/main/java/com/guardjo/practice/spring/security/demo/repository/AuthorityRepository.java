package com.guardjo.practice.spring.security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guardjo.practice.spring.security.demo.domain.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}
