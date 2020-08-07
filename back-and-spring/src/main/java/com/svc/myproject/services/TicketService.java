package com.svc.myproject.services;

import com.svc.myproject.domain.models.services.TicketServiceModel;

import java.util.List;

public interface TicketService {
    List<TicketServiceModel> allTickets();

    void create(TicketServiceModel ticketServiceModel);

    void update(TicketServiceModel ticketServiceModel, String id);

    void delete(String id);

    boolean findTicketById(String id);
}
