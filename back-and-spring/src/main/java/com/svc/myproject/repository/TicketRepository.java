package com.svc.myproject.repository;

import com.svc.myproject.domain.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {
    Ticket findByNumber(int number);
}
