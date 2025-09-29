package com.arnab_saha.tickets.domain;

import com.arnab_saha.tickets.domain.entities.TicketStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTicketRequest {
    private UUID id;
    private TicketStatusEnum status;
    private String description;
}
