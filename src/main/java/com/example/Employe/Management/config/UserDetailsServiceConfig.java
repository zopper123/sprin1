package com.example.Employe.Management.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserDetailsServiceConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        UserDetails user = User.withUsername("user")
                .password(passwordEncoder.encode("user123"))
                .roles("USER")
                .build();

        UserDetails operator = User.withUsername("operator")
                .password(passwordEncoder.encode("operator123"))
                .roles("OPERATOR")
                .build();

        UserDetails editor = User.withUsername("editor")
                .password(passwordEncoder.encode("editor123"))
                .roles("EDITOR")
                .build();

        return new InMemoryUserDetailsManager(admin, user, operator, editor);
    }
}