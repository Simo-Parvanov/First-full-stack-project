package com.svc.myproject.services.impl;

import com.svc.myproject.domain.entities.ERole;
import com.svc.myproject.domain.entities.Role;
import com.svc.myproject.domain.entities.User;
import com.svc.myproject.payload.request.SignupRequest;
import com.svc.myproject.repository.UserRepository;
import com.svc.myproject.services.RoleService;
import com.svc.myproject.services.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public void createUser(SignupRequest signUpRequest) {
        roleService.seedRole();
        int a = 0;
        Set<Role> roles = new HashSet<>();
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()));

        if (userRepository.count() == 0){
            roles.add(roleService.findByRoleName(ERole.ROLE_ADMIN));
            roles.add(roleService.findByRoleName(ERole.ROLE_MODERATOR));
        }else {
            roles.add(roleService.findByRoleName(ERole.ROLE_USER));
        }

        user.setRoles(roles);
        userRepository.saveAndFlush(user);
    }

    @Override
    public boolean findUserByName(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean findUserByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
