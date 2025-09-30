package com.arnab_saha.tickets.controllers;

import com.arnab_saha.tickets.domain.dtos.ErrorDto;
import com.arnab_saha.tickets.exceptions.TicketException;
import com.arnab_saha.tickets.exceptions.TicketNotFoundException;
import com.arnab_saha.tickets.exceptions.TicketUpdateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(TicketNotFoundException.class)
    public ResponseEntity<ErrorDto> handleTicketNotFoundException(
            TicketNotFoundException ex
    ) {
        log.error("Caught TicketNotFoundException", ex);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setError("Unable to find ticket");
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TicketUpdateException.class)
    public ResponseEntity<ErrorDto> handleTicketUpdateException(
            TicketException ex
    ) {
        log.error("Caught TicketUpdateException", ex);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setError("Missing information to update ticket");
        return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TicketException.class)
    public ResponseEntity<ErrorDto> handleTicketException(
            TicketException ex
    ) {
        log.error("Caught TicketException", ex);
        ErrorDto errorDto = new ErrorDto();
        errorDto.setError("Something went wrong with the ticket");
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
