package com.arnab_saha.tickets.services;

import com.arnab_saha.tickets.domain.CreateCommentRequest;
import com.arnab_saha.tickets.domain.entities.Comment;

import java.util.UUID;

public interface CommentService {
    Comment createComment(UUID creatorId, UUID ticketId, CreateCommentRequest comment);
}
