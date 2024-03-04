package com.guardjo.practice.spring.security.demo.domain;

import java.util.ArrayList;
import java.util.List;

import com.guardjo.practice.spring.security.demo.domain.types.AlgorithmType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "ACCOUNT")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@ToString
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 45, unique = true)
	private String username;
	@Column(columnDefinition = "TEXT")
	private String password;
	@Enumerated(EnumType.STRING)
	private AlgorithmType algorithm;

	@Builder.Default
	@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
	private List<Authority> authorities = new ArrayList<>();
}
