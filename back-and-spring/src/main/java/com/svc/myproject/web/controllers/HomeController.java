package com.svc.myproject.web.controllers;

import com.svc.myproject.domain.entities.User;
import com.svc.myproject.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/mod")
public class HomeController {
    private final UserRepository userRepository;

    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    @PreAuthorize ("hasRole('ROLE_MODERATOR')")
    public ResponseEntity<List<User>> allUsers(){
       List<User> u = userRepository.findAll();
        System.out.println();
        return ResponseEntity.ok(u);
    }
    @DeleteMapping("/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<User>> dellUser(@PathVariable String username){
        userRepository.deleteById(userRepository.findByUsername(username).get().getId());
        List<User> u = userRepository.findAll();
        return ResponseEntity.ok(u);
    }
}
