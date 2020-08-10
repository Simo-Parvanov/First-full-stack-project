package com.svc.myproject.domain.models.services;

public class UpdateRoleServiceModel {
   private String username;
   private String method;

    public UpdateRoleServiceModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
