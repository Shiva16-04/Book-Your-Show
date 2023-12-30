package com.example.book_your_show.exceptions;

public class AddressAlreadyPresentException extends Exception{
    public AddressAlreadyPresentException(String message) {
        super(message);
    }
}
