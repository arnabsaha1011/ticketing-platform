package com.arnab_saha.tickets.services.impl;

import com.arnab_saha.tickets.domain.CreateTicketRequest;
import com.arnab_saha.tickets.domain.UpdateTicketRequest;
import com.arnab_saha.tickets.domain.entities.Ticket;
import com.arnab_saha.tickets.domain.entities.TicketStatusEnum;
import com.arnab_saha.tickets.domain.entities.User;
import com.arnab_saha.tickets.exceptions.TicketUpdateException;
import com.arnab_saha.tickets.repositories.TicketRepository;
import com.arnab_saha.tickets.repositories.UserRepository;
import com.arnab_saha.tickets.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    public final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    @Override
    public Ticket createTicket(UUID creatorId, CreateTicketRequest ticket) {
        User creator = userRepository.getReferenceById(creatorId);
        Ticket ticketToCreate = Ticket.builder()
                .status(TicketStatusEnum.NEW)
                .description(ticket.getDescription())
                .creator(creator)
                .build();
        return ticketRepository.save(ticketToCreate);
    }

    @Override
    public Page<Ticket> listTicketsForCustomer(UUID customerId, Pageable pageable) {
        return ticketRepository.findByCreatorId(customerId, pageable);
    }

    @Override
    public Ticket updateTicketForCustomer(UUID creatorId, UUID ticketId, UpdateTicketRequest ticket) {
        if (null == ticketId) {
            throw new TicketUpdateException("Id is required in ticket to update the ticket");
        }

        if (null == creatorId) {
            throw new TicketUpdateException("Creator Id cannot be null while updating the ticket");
        }

        Ticket existingTicket = ticketRepository.findByIdAndCreatorId(ticketId, creatorId)
                .orElseThrow(() -> new TicketUpdateException(
                        String.format("No ticket found for ticket Id = '%s' and creator Id = '%s'", ticket.getId(), creatorId)));

        existingTicket.setDescription(ticket.getDescription());
        existingTicket.setStatus(ticket.getStatus());

        return ticketRepository.save(existingTicket);
    }
}
