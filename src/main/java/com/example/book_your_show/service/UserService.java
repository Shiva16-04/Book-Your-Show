package com.example.book_your_show.service;

import com.example.book_your_show.requestDTO.UserEmailRequest;
import com.example.book_your_show.requestDTO.UserRequest;

public interface UserService {
    public String addUser(UserRequest userRequest)throws Exception;
    public String sendEmailValidationCode(UserEmailRequest userEmailRequest)throws Exception;
    private boolean mailValidation(String email, String code)throws Exception{
        return true;
    }
}
