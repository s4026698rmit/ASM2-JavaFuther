package com.groupX.ems.repositories;

import com.groupX.ems.models.Ticket;

import java.util.List;
import java.util.Optional;

public interface TicketRepository {
    Optional<Ticket> findById(long id);
    List<Ticket> findAll();
    Ticket save(Ticket ticket);
    void delete(long id);
}

