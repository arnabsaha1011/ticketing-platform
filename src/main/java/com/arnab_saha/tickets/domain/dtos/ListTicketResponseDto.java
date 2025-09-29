package com.arnab_saha.tickets.domain.dtos;

import com.arnab_saha.tickets.domain.entities.TicketStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListTicketResponseDto {
    private UUID id;
    private TicketStatusEnum status;
    private String creatorId;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
