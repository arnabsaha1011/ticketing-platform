package com.arnab_saha.tickets.controllers;

import com.arnab_saha.tickets.domain.dtos.CreateCommentRequestDto;
import com.arnab_saha.tickets.domain.dtos.CreateCommentResponseDto;
import com.arnab_saha.tickets.domain.entities.Comment;
import com.arnab_saha.tickets.domain.entities.Ticket;
import com.arnab_saha.tickets.domain.entities.UserRole;
import com.arnab_saha.tickets.exceptions.TicketException;
import com.arnab_saha.tickets.mappers.CommentMapper;
import com.arnab_saha.tickets.services.CommentService;
import com.arnab_saha.tickets.services.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.arnab_saha.tickets.utils.JwtUtils.parseUserId;
import static com.arnab_saha.tickets.utils.JwtUtils.parseUserRole;

@RestController
@RequestMapping(path = "api/v1/tickets/{ticketId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final CommentMapper commentMapper;

    @PostMapping
    public ResponseEntity<CreateCommentResponseDto> createComment(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID ticketId,
            @Valid @RequestBody CreateCommentRequestDto createCommentRequestDto
    ) {
        if (null == ticketId) {
            throw new TicketException("Ticket Id is required");
        }

        UUID creatorId = parseUserId(jwt);
        UserRole role = parseUserRole(jwt);

        Comment comment = commentService.createComment(creatorId, ticketId, role, commentMapper.fromCreateCommentRequestDto(createCommentRequestDto));
        return ResponseEntity.ok(commentMapper.toCreateCommentResponseDto(comment));
    }
}
