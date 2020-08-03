package com.svc.myproject.services.impl;

import com.svc.myproject.domain.entities.ERole;
import com.svc.myproject.domain.entities.Role;
import com.svc.myproject.repository.RoleRepository;
import com.svc.myproject.services.RoleService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void seedRole() {
        if (roleRepository.count() == 0) {
            for (ERole eRole : ERole.values()) {
                Role role = new Role();
                role.setName(eRole);
                roleRepository.saveAndFlush(role);
            }
        }
    }

    @Override
    public Set<Role> getAllRole() {
        return new HashSet<>(roleRepository.findAll());
    }

    @Override
    public Role findByRoleName(ERole name) {
        return roleRepository.findByName(name).orElse(null);
    }
}
