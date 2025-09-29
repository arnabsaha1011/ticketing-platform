package com.arnab_saha.tickets.exceptions;

public class TicketException extends RuntimeException {
    public TicketException() {
        super();
    }

    public TicketException(String message) {
        super(message);
    }

    public TicketException(String message, Throwable cause) {
        super(message, cause);
    }

    public TicketException(Throwable cause) {
        super(cause);
    }
}
