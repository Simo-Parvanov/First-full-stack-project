package com.svc.myproject.services;

import com.svc.myproject.domain.entities.ERole;
import com.svc.myproject.domain.entities.Role;

import java.util.Set;

public interface RoleService {
    void seedRole();
    Set<Role> getAllRole();
    Role findByRoleName(ERole name);
}
