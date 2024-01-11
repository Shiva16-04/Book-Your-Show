package com.example.book_your_show.exceptions;

public class TicketCannotBeBookedException extends Exception{
    public TicketCannotBeBookedException(String message) {
        super(message);
    }
}
