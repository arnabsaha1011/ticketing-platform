package com.arnab_saha.tickets.domain.dtos;

import com.arnab_saha.tickets.domain.entities.TicketStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTicketResponseDto {
    private UUID id;
    private TicketStatusEnum status;
    private String description;
    private List<GetTicketCommentResponseDto> comments = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
