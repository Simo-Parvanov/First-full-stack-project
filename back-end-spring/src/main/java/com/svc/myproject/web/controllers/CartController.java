package com.svc.myproject.web.controllers;

import com.svc.myproject.domain.models.services.CartServiceModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/cart")
public class CartController {

    @GetMapping()
    public ResponseEntity<?> allCart(){
    return ResponseEntity.ok().build();
    }

    @PostMapping()
    public ResponseEntity<?> addCart(@RequestBody CartServiceModel cartServiceModel){
        return ResponseEntity.ok().build();
    }
}
