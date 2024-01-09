package com.example.book_your_show.exceptions;

public class MovieAlreadyPresentException extends Exception {

    public MovieAlreadyPresentException(String message) {
        super(message);
    }

}