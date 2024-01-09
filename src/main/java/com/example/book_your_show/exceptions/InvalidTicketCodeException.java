package com.example.book_your_show.exceptions;

public class InvalidTicketCodeException extends Exception{
    public InvalidTicketCodeException(String message) {
        super(message);
    }
}
