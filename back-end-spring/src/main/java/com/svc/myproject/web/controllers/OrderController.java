package com.svc.myproject.web.controllers;

import com.svc.myproject.domain.models.services.OrderServiceModel;
import com.svc.myproject.services.OrderService;
import com.svc.myproject.services.impl.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final EmailService emailService;

    public OrderController(OrderService orderService, EmailService emailService) {
        this.orderService = orderService;
        this.emailService = emailService;
    }


    @PostMapping("/create")
    public ResponseEntity<OrderServiceModel> createOrder(@RequestBody OrderServiceModel orderServiceModel,
                                                         UriComponentsBuilder builder){
        orderService.createOrder(orderServiceModel);
        return ResponseEntity.created(builder.path("/order/create")
                .buildAndExpand().toUri()).build();

    }
    @GetMapping("/send")
    public void sendEmail(){
        System.out.println();
        emailService.sendSimpleMessage("ad","da","da");
    }
}
