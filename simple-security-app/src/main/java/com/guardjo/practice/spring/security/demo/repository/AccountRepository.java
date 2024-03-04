package com.guardjo.practice.spring.security.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guardjo.practice.spring.security.demo.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
	Optional<Account> findByUsername(String username);
}
