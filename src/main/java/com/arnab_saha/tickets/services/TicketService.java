package com.arnab_saha.tickets.services;

import com.arnab_saha.tickets.domain.CreateTicketRequest;
import com.arnab_saha.tickets.domain.UpdateTicketRequest;
import com.arnab_saha.tickets.domain.entities.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TicketService {
    Ticket createTicket(UUID creatorId, CreateTicketRequest ticket);

    Page<Ticket> listTicketsForCustomer(UUID creatorId, Pageable pageable);

    Ticket updateTicketForCustomer(UUID creatorId, UUID ticketId, UpdateTicketRequest ticket);
}
