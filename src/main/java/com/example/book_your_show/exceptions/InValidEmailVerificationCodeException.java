package com.example.book_your_show.exceptions;

public class InValidEmailVerificationCodeException extends Exception{
    public InValidEmailVerificationCodeException(String message) {
        super(message);
    }
}
