package com.arnab_saha.tickets.controllers;

import com.arnab_saha.tickets.domain.CreateTicketRequest;
import com.arnab_saha.tickets.domain.UpdateTicketRequest;
import com.arnab_saha.tickets.domain.dtos.*;
import com.arnab_saha.tickets.domain.entities.Ticket;
import com.arnab_saha.tickets.exceptions.TicketNotFoundException;
import com.arnab_saha.tickets.mappers.TicketMapper;
import com.arnab_saha.tickets.services.TicketService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.arnab_saha.tickets.utils.JwtUtils.parseUserId;

@RestController
@RequestMapping(path = "api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketMapper ticketMapper;
    private final TicketService ticketService;

    @PostMapping
    @Transactional
    public ResponseEntity<CreateTicketResponseDto> createTicket(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody CreateTicketRequestDto createTicketRequestDto
            ) {
        CreateTicketRequest createTicketRequest = ticketMapper.fromCreateTicketRequestDto(createTicketRequestDto);
        UUID creatorId = parseUserId(jwt);
        Ticket ticket = ticketService.createTicket(creatorId, createTicketRequest);
        CreateTicketResponseDto createTicketResponseDto = ticketMapper.toCreateTicketResponseDto(ticket);
        return ResponseEntity.ok(createTicketResponseDto);
    }

    @GetMapping
    public ResponseEntity<Page<ListTicketResponseDto>> getTickets(
            @AuthenticationPrincipal Jwt jwt,
            Pageable pageable
    ) {
        UUID userId = parseUserId(jwt);
        Page<Ticket> tickets = ticketService.listTicketsForCustomer(userId, pageable);
        return ResponseEntity.ok(tickets.map(ticketMapper::toListTicketResponseDto));
    }

    @PutMapping(path = "/{ticketId}")
    public ResponseEntity<UpdateTicketResponseDto> updateTicketForCreator(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID ticketId,
            @Valid @RequestBody UpdateTicketRequestDto updateTicketRequestDto) {

        UUID creatorId = parseUserId(jwt);
        UpdateTicketRequest updateTicketRequest = ticketMapper.fromUpdateTicketRequestDto(updateTicketRequestDto);
        Ticket updatedTicket = ticketService.updateTicketForCustomer(creatorId, ticketId, updateTicketRequest);
        return ResponseEntity.ok(ticketMapper.toUpdateTicketResponseDto(updatedTicket));
    }

    @GetMapping(path = "/{ticketId}")
    public ResponseEntity<GetTicketResponseDto> getTicketForCreator(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID ticketId
    ) {
        UUID creatorId = parseUserId(jwt);
        Ticket ticket = ticketService.getTicketForCustomer(creatorId, ticketId)
                .orElseThrow(() -> new TicketNotFoundException(
                        String.format("No ticket found for this ticket ID = '%s'", ticketId)
                ));

        return ResponseEntity.ok(ticketMapper.toGetTicketResponseDto(ticket));
    }
}
