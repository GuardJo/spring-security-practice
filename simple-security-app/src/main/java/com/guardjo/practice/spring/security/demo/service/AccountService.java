package com.guardjo.practice.spring.security.demo.service;

import org.springframework.stereotype.Service;

import com.guardjo.practice.spring.security.demo.domain.Account;
import com.guardjo.practice.spring.security.demo.repository.AccountRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccountService {
	private final AccountRepository accountRepository;

	public Account findAccount(String username) {
		log.info("Find Account Entity, username = {}", username);

		return accountRepository.findByUsername(username)
			.orElseThrow(EntityNotFoundException::new);
	}
}
