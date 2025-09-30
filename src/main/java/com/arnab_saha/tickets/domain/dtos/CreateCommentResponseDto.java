package com.arnab_saha.tickets.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentResponseDto {
    private UUID id;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
