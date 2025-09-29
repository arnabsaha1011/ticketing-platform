package com.arnab_saha.tickets.repositories;

import com.arnab_saha.tickets.domain.entities.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {
    Page<Ticket> findByCreatorId(UUID creatorId, Pageable pageable);

    Optional<Ticket> findByIdAndCreatorId(UUID id, UUID creatorId);
}
