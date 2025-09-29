package com.arnab_saha.tickets.repositories;

import com.arnab_saha.tickets.domain.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentRepository extends JpaRepository<Comment, UUID> {
}
