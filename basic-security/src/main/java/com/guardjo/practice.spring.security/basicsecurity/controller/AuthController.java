package com.guardjo.practice.spring.security.basicsecurity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AuthController {

    @GetMapping("/auth")
    public String read(Authentication authentication) {
        String username = authentication.getName();

        log.info("Request Read, username = {}", username);

        return "Successes";
    }

    @PostMapping("/auth")
    public String write(Authentication authentication) {
        String username = authentication.getName();

        log.info("Request Write, username = {}", username);

        return "Successes";
    }

    @GetMapping("/access")
    public String readAccess(Authentication authentication) {
        String username = authentication.getName();

        log.info("Request Access Read, username = {}", username);

        return "Successes";
    }

    @PostMapping("/access")
    public String writeAccess(Authentication authentication) {
        String username = authentication.getName();

        log.info("Request Access Write, username = {}", username);

        return "Successes";
    }
}
