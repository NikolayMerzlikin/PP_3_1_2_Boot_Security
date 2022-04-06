package ru.kata.spring.boot_security.demo.models;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;

public enum Roles implements GrantedAuthority {
    ADMIN, USER;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}
