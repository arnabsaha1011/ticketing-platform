package com.arnab_saha.tickets.services.impl;

import com.arnab_saha.tickets.domain.CreateCommentRequest;
import com.arnab_saha.tickets.domain.entities.Comment;
import com.arnab_saha.tickets.domain.entities.Ticket;
import com.arnab_saha.tickets.domain.entities.User;
import com.arnab_saha.tickets.domain.entities.UserRole;
import com.arnab_saha.tickets.exceptions.TicketException;
import com.arnab_saha.tickets.exceptions.TicketNotFoundException;
import com.arnab_saha.tickets.repositories.CommentRepository;
import com.arnab_saha.tickets.repositories.TicketRepository;
import com.arnab_saha.tickets.repositories.UserRepository;
import com.arnab_saha.tickets.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    @Override
    public Comment createComment(UUID userId, UUID ticketId, UserRole role, CreateCommentRequest createCommentRequest) {
        if (null == userId) {
            throw new TicketException("Creator ID cannot be null");
        }

        if (null == ticketId) {
            throw new TicketException("Ticket ID cannot be null");
        }

        Ticket ticket;
        if (UserRole.ROLE_END_USER == role) {
            ticket = ticketRepository.findByIdAndCreatorId(ticketId, userId)
                    .orElseThrow(() -> new TicketNotFoundException(
                            String.format("No ticket found for ticket ID = '%s' and creator Id = '%s'", ticketId, userId)));
        } else if (UserRole.ROLE_AGENT == role) {
            ticket = ticketRepository.findById(ticketId)
                    .orElseThrow(() -> new TicketNotFoundException(
                            String.format("No ticket found for ticket ID = '%s' and creator Id = '%s'", ticketId, userId)));
        } else {
            throw new TicketException(
                    String.format("Access denied for user ID = '%s' to ticket ID = '%s'", userId, ticketId));
        }

        User commenter = userRepository.getReferenceById(userId);
        Comment comment = Comment.builder()
                .comment(commenter.getName() + ", " + commenter.getRole() + " : " + createCommentRequest.getComment())
                .commenter(commenter)
                .ticket(ticket)
                .build();
        return commentRepository.save(comment);
    }
}
