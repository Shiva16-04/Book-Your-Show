package com.example.book_your_show.exceptions;

public class TicketCannotBeCancelledException extends Exception{
    public TicketCannotBeCancelledException(String message) {
        super(message);
    }
}
