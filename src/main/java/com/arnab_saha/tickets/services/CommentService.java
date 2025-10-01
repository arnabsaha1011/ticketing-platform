package com.arnab_saha.tickets.services;

import com.arnab_saha.tickets.domain.CreateCommentRequest;
import com.arnab_saha.tickets.domain.entities.Comment;
import com.arnab_saha.tickets.domain.entities.UserRole;

import java.util.UUID;

public interface CommentService {
    Comment createComment(UUID userId, UUID ticketId, UserRole role, CreateCommentRequest comment);
}
