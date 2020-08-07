package com.svc.myproject.services.impl;

import com.svc.myproject.domain.entities.ERole;
import com.svc.myproject.domain.entities.Role;
import com.svc.myproject.domain.entities.User;
import com.svc.myproject.domain.models.services.UpdateRoleServiceModel;
import com.svc.myproject.domain.models.services.UserServiceModel;
import com.svc.myproject.payload.request.SignupRequest;
import com.svc.myproject.repository.UserRepository;
import com.svc.myproject.services.RoleService;
import com.svc.myproject.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final ModelMapper mapper;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, RoleService roleService, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.mapper = mapper;
    }

    @Override
    public void createUser(SignupRequest signUpRequest) {
        roleService.seedRole();
        Set<Role> roles = new HashSet<>();
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()));

        if (userRepository.count() == 0) {
            roles.add(roleService.findByRoleName(ERole.ROLE_MODERATOR));
            roles.add(roleService.findByRoleName(ERole.ROLE_ADMIN));

        } else {
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

    @Override
    public List<UserServiceModel> roleUpdate(UpdateRoleServiceModel update) {
        Optional<User> user = userRepository.findByUsername(update.getUsername());
        if (user.isEmpty()) {
            return null;
        }
        if (update.getMethod().equals("update")) {
            Set<Role> roles = user.get().getRoles();
            roles.add(roleService.findByRoleName(ERole.ROLE_MODERATOR));
            user.get().setRoles(roles);
        }
        if (update.getMethod().equals("delete")) {
            Set<Role> roles = user.get().getRoles();
            roles.remove(roleService.findByRoleName(ERole.ROLE_MODERATOR));
            user.get().setRoles(roles);
        }
        userRepository.saveAndFlush(user.get());
        return userRepository.findAll().stream().map(user1 ->
                mapper.map(user1, UserServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public List<UserServiceModel> allUsers() {
        return userRepository.findAll().stream().map(user -> {
            UserServiceModel model = mapper.map(user, UserServiceModel.class);
            List<Role> list = new ArrayList<>(user.getRoles());
            List<Role> collect = list.stream().sorted((a, b) -> a.getName().compareTo(b.getName()))
                    .collect(Collectors.toList());
            model.setRoles(collect);
            return model;
        }).collect(Collectors.toList());
    }

    @Override
    public List<UserServiceModel> deleteUser(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            return null;
        }
        userRepository.delete(user.get());

        return userRepository.findAll().stream().map(user1 -> {
            return mapper.map(user1, UserServiceModel.class);
        }).collect(Collectors.toList());
    }

}
