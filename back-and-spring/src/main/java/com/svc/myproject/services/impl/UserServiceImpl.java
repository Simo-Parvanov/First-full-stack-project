package com.svc.myproject.services.impl;

import com.svc.myproject.domain.entities.ERole;
import com.svc.myproject.domain.entities.Role;
import com.svc.myproject.domain.entities.User;
import com.svc.myproject.domain.models.services.UserServiceModel;
import com.svc.myproject.payload.request.SignupRequest;
import com.svc.myproject.repository.UserRepository;
import com.svc.myproject.services.RoleService;
import com.svc.myproject.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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

    @Override
    public List<UserServiceModel> roleUpdate(String username, String method, String roleName) {
       Optional<User> user =  userRepository.findByUsername(username);
       if (user.isEmpty()) {
           return null;
       }
       if (method.equals("update")){
         Set<Role> rr =  user.get().getRoles();
         rr.add(roleService.findByRoleName(ERole.valueOf(roleName)));
         user.get().setRoles(rr);
       }
        userRepository.saveAndFlush(user.get());
        return userRepository.findAll().stream().map(user1 -> mapper.map(user1, UserServiceModel.class)).collect(Collectors.toList());
    }
}
