package com.arnab_saha.tickets.domain;

import com.arnab_saha.tickets.domain.entities.TicketStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTicketRequest {
    private TicketStatusEnum status;
    private String description;
}
