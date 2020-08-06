package com.svc.myproject.domain.models.services;

import com.svc.myproject.domain.entities.Role;

import java.util.List;
import java.util.Set;

public class UserServiceModel {
    private Long id;
    private String username;
    private String email;
    private List<Role> roles;

    public UserServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
