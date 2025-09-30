package com.arnab_saha.tickets.mappers;

import com.arnab_saha.tickets.domain.CreateCommentRequest;
import com.arnab_saha.tickets.domain.dtos.CreateCommentRequestDto;
import com.arnab_saha.tickets.domain.dtos.CreateCommentResponseDto;
import com.arnab_saha.tickets.domain.entities.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {
    CreateCommentRequest fromCreateCommentRequestDto(CreateCommentRequestDto dto);

    CreateCommentResponseDto toCreateCommentResponseDto(Comment comment);
}
