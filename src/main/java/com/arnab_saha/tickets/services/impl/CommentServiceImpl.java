package com.arnab_saha.tickets.services.impl;

import com.arnab_saha.tickets.domain.CreateCommentRequest;
import com.arnab_saha.tickets.domain.entities.Comment;
import com.arnab_saha.tickets.domain.entities.Ticket;
import com.arnab_saha.tickets.exceptions.TicketException;
import com.arnab_saha.tickets.exceptions.TicketNotFoundException;
import com.arnab_saha.tickets.repositories.CommentRepository;
import com.arnab_saha.tickets.repositories.TicketRepository;
import com.arnab_saha.tickets.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final TicketRepository ticketRepository;

    @Override
    public Comment createComment(UUID creatorId, UUID ticketId, CreateCommentRequest createCommentRequest) {
        if (null == creatorId) {
            throw new TicketException("Creator ID cannot be null");
        }

        if (null == ticketId) {
            throw new TicketException("Ticket ID cannot be null");
        }

        Ticket ticket = ticketRepository.findByIdAndCreatorId(ticketId, creatorId)
                .orElseThrow(() -> new TicketNotFoundException(
                        String.format("No ticket found for ticket ID = '%s' and creator Id = '%s'", ticketId, creatorId)));

        Comment comment = Comment.builder()
                .comment(createCommentRequest.getComment())
                .ticket(ticket)
                .build();
        return commentRepository.save(comment);
    }
}
