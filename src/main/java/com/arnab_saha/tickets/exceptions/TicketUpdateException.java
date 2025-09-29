package com.arnab_saha.tickets.exceptions;

public class TicketUpdateException extends TicketException {
    public TicketUpdateException() {
        super();
    }

    public TicketUpdateException(String message) {
        super(message);
    }

    public TicketUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public TicketUpdateException(Throwable cause) {
        super(cause);
    }
}
