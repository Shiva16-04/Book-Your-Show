package com.example.book_your_show.exceptions;

public class MaxLimitReachedToAddException extends Exception{
    public MaxLimitReachedToAddException(String message) {
        super(message);
    }
}
