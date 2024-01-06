package com.example.book_your_show.exceptions;

public class GenreAlreadyPresentException extends Exception{
    public GenreAlreadyPresentException(String message) {
        super(message);
    }
}
