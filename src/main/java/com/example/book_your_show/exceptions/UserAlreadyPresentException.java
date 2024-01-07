package com.example.book_your_show.exceptions;

public class UserAlreadyPresentException extends Exception{
    public UserAlreadyPresentException(String message) {
        super(message);
    }
}
