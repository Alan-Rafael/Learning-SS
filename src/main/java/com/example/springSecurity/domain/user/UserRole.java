package com.example.springSecurity.domain.user;

import javax.management.relation.Role;

public enum UserRole {
    ADMIN("admin"),
    USER("user");

    private String role;
    UserRole(String role){
        this.role = role;
    }

    private String getRole(){
        return role;
    }
}
