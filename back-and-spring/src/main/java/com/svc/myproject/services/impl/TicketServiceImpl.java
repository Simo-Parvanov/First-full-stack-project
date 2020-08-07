package com.svc.myproject.services.impl;

import com.svc.myproject.domain.entities.Ticket;
import com.svc.myproject.domain.models.services.TicketServiceModel;
import com.svc.myproject.repository.TicketRepository;
import com.svc.myproject.services.TicketService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final ModelMapper mapper;

    public TicketServiceImpl(TicketRepository ticketRepository, ModelMapper mapper) {
        this.ticketRepository = ticketRepository;
        this.mapper = mapper;
    }

    @Override
    public List<TicketServiceModel> allTickets() {
        return ticketRepository.findAll().stream().map(ticket -> {
            return mapper.map(ticket, TicketServiceModel.class);
        }).collect(Collectors.toList());
    }

    @Override
    public void create(TicketServiceModel ticketServiceModel) {
        Ticket ticket = mapper.map(ticketServiceModel, Ticket.class);
        ticket.setCreateDate(LocalDate.now());
        ticket.setNumber((int) ticketRepository.count() + 1);
        ticket.setStatus(true);
        ticketRepository.saveAndFlush(ticket);
    }

    @Override
    public void update(TicketServiceModel ticketServiceModel, String id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        ticket.get().setStatus(ticketServiceModel.isStatus());
        ticket.get().setLastUpdate(LocalDate.now());
        ticketRepository.saveAndFlush(ticket.get());

    }

    @Override
    public void delete(String id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public boolean findTicketById(String id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isPresent()){
            return false;
        }
        return true;
    }
}
