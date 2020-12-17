package com.svc.myproject.web.controllers;

import com.svc.myproject.domain.models.services.UpdateRoleServiceModel;
import com.svc.myproject.domain.models.services.UserServiceModel;
import com.svc.myproject.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/mod")
public class AdminController {
    private final UserService userService;

    public AdminController( UserService userService) {
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
    public ResponseEntity<List<UserServiceModel>> dellUser(@PathVariable String username){
        List<UserServiceModel> userServiceModels = userService.deleteUser(username);
        if (userServiceModels == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.noContent().build();
//        return ResponseEntity.ok(userServiceModels);
    }
    
    @PostMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserServiceModel>> sendOrDeleteRole(
            @RequestBody UpdateRoleServiceModel updateRoleServiceModel,
            UriComponentsBuilder builder){
        List<UserServiceModel> userServiceModels = userService.roleUpdate(updateRoleServiceModel);

        if (userServiceModels == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.created(builder.path("/api/auth/signup")
                .buildAndExpand().toUri()).build();
//        return new ResponseEntity<>(userServiceModels, HttpStatus.OK);
    }
}
