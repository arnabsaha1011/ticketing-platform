package com.arnab_saha.tickets.controllers;

import com.arnab_saha.tickets.domain.CreateTicketRequest;
import com.arnab_saha.tickets.domain.dtos.CreateTicketRequestDto;
import com.arnab_saha.tickets.domain.dtos.CreateTicketResponseDto;
import com.arnab_saha.tickets.domain.entities.Ticket;
import com.arnab_saha.tickets.domain.entities.User;
import com.arnab_saha.tickets.mappers.TicketMapper;
import com.arnab_saha.tickets.services.TicketService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketMapper ticketMapper;
    private final TicketService ticketService;

    @PostMapping
    @Transactional
    public ResponseEntity<CreateTicketResponseDto> createTicket(
            @Valid @RequestBody CreateTicketRequestDto createTicketRequestDto
            ) {
        CreateTicketRequest createTicketRequest = ticketMapper.fromCreateTicketRequestDto(createTicketRequestDto);

        Ticket ticket = ticketService.createTicket(createTicketRequest);
        CreateTicketResponseDto createTicketResponseDto = ticketMapper.toCreateTicketResponseDto(ticket);
        return ResponseEntity.ok(createTicketResponseDto);
    }
}
