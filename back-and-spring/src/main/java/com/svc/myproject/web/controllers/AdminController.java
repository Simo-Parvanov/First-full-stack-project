package com.svc.myproject.web.controllers;

import com.svc.myproject.domain.entities.User;
import com.svc.myproject.domain.models.services.UserServiceModel;
import com.svc.myproject.repository.UserRepository;
import com.svc.myproject.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/mod")
public class AdminController {
    private final UserRepository userRepository;
    private final UserService userService;

    public AdminController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize ("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<List<UserServiceModel>> allUsers(){
       List<UserServiceModel> u = userService.allUsers();
        return ResponseEntity.ok(u);
    }
    @DeleteMapping("/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<User>> dellUser(@PathVariable String username){
        userRepository.deleteById(userRepository.findByUsername(username).get().getId());
        List<User> u = userRepository.findAll();
        return ResponseEntity.ok(u);
    }
    
    @PostMapping("/{username}/{method}/{role}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserServiceModel>> sendOrDeleteRole(@PathVariable String username,
                                                                   @PathVariable String method,
                                                                   @PathVariable String role){
        List<UserServiceModel> userServiceModels = userService.roleUpdate(username,method,role);
        if (userServiceModels == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userServiceModels, HttpStatus.OK);
    }
}
