package com.guardjo.practice.spring.security.basicsecurity.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(registry -> {
                    registry.requestMatchers(PathRequest.toH2Console()).permitAll()
                            .requestMatchers(HttpMethod.GET, "/auth/**")
                            .hasAnyAuthority("READ", "WRITE")
                            .requestMatchers(HttpMethod.POST, "/auth/**")
                            .hasAuthority("WRITE")
                            .anyRequest().authenticated();
                })
                .csrf(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(custom -> {
                    custom.successHandler(new CustomSuccessHandler());
                    custom.failureHandler(new CustomFailureHandler());
                });
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        String userByUsernameQuery = "select u.username, u.password, u.enabled from dummy_user u where u.username = ?";
        String authsByUserQuery = "select a.username, a.authority from dummy_authority a where a.username = ?";

        var userDetailsService = new JdbcUserDetailsManager(dataSource);

        userDetailsService.setUsersByUsernameQuery(userByUsernameQuery);
        userDetailsService.setAuthoritiesByUsernameQuery(authsByUserQuery);

        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
