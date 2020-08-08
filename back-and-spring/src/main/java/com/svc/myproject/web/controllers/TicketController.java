package com.svc.myproject.web.controllers;

import com.svc.myproject.domain.models.services.TicketServiceModel;
import com.svc.myproject.services.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/ticket")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR')")
    public ResponseEntity<List<TicketServiceModel>> getAllTickets() {
        return ResponseEntity.ok(ticketService.allTickets());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTicket(@RequestBody TicketServiceModel ticketServiceModel,
                                          UriComponentsBuilder builder){
        ticketService.create(ticketServiceModel);
        return ResponseEntity.created(builder.path("/ticket/create")
                .buildAndExpand().toUri()).build();
    }
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR')")
    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateTicket(@RequestBody TicketServiceModel ticketServiceModel,
                                          UriComponentsBuilder builder, @PathVariable String id){
        if (ticketService.findTicketById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ticketService.update(ticketServiceModel, id);
        return ResponseEntity.created(builder.path("/ticket/update")
                .buildAndExpand().toUri()).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTicket(@PathVariable String id){
        if (ticketService.findTicketById(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ticketService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
