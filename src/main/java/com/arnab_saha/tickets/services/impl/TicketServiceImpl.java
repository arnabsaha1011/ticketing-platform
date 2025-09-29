package com.arnab_saha.tickets.services.impl;

import com.arnab_saha.tickets.domain.CreateTicketRequest;
import com.arnab_saha.tickets.domain.entities.Ticket;
import com.arnab_saha.tickets.domain.entities.TicketStatusEnum;
import com.arnab_saha.tickets.repositories.TicketRepository;
import com.arnab_saha.tickets.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    public final TicketRepository ticketRepository;

    @Override
    public Ticket createTicket(CreateTicketRequest ticket) {
        Ticket ticketToCreate = Ticket.builder()
                .status(TicketStatusEnum.NEW)
                .description(ticket.getDescription())
                .build();
        return ticketRepository.save(ticketToCreate);
    }
}
