package com.arnab_saha.tickets.mappers;

import com.arnab_saha.tickets.domain.CreateTicketRequest;
import com.arnab_saha.tickets.domain.UpdateTicketRequest;
import com.arnab_saha.tickets.domain.dtos.*;
import com.arnab_saha.tickets.domain.entities.Comment;
import com.arnab_saha.tickets.domain.entities.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TicketMapper {
    CreateTicketRequest fromCreateTicketRequestDto(CreateTicketRequestDto dto);

    CreateTicketResponseDto toCreateTicketResponseDto(Ticket ticket);

    ListTicketResponseDto toListTicketResponseDto(Ticket ticket);

    UpdateTicketRequest fromUpdateTicketRequestDto(UpdateTicketRequestDto dto);

    UpdateTicketResponseDto toUpdateTicketResponseDto(Ticket ticket);

    GetTicketResponseDto toGetTicketResponseDto(Ticket ticket);

    GetTicketCommentResponseDto toGetTicketCommentResponseDto(Comment comment);
}
