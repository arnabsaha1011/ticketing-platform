package com.arnab_saha.tickets.services;

import com.arnab_saha.tickets.domain.CreateTicketRequest;
import com.arnab_saha.tickets.domain.entities.Ticket;

import java.util.UUID;

public interface TicketService {
    Ticket createTicket(CreateTicketRequest ticket);
}
