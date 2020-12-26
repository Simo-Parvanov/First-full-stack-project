package com.svc.myproject.web.controllers;

import com.svc.myproject.domain.models.services.OrderServiceModel;
import com.svc.myproject.domain.models.services.OrderUpdateModel;
import com.svc.myproject.services.EmailService;
import com.svc.myproject.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

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
    @GetMapping("/search/{number}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR')")
    public ResponseEntity<OrderServiceModel> getOrderByOrderNumber(@PathVariable String number){
       OrderServiceModel orderServiceModel = orderService.findOrderByOrderNumber(number);
        if (orderServiceModel == null){
            return new  ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(orderServiceModel);

    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR')")
    public ResponseEntity<List<OrderServiceModel>> getAllOrders(){
        List<OrderServiceModel> orderServiceModel = orderService.getAllOrders();
        return ResponseEntity.ok(orderServiceModel);

    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR')")
    public ResponseEntity<OrderServiceModel> updateOrderByID(@PathVariable String id,
                                                             @RequestBody OrderUpdateModel updateModel){
        if(orderService.findOrderById(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        OrderServiceModel orderServiceModel = orderService.update(id, updateModel);
       return new ResponseEntity<>(orderServiceModel, HttpStatus.OK);
    }


}
