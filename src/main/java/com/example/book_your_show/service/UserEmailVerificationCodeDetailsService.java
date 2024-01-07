package com.example.book_your_show.service;

import com.example.book_your_show.entities.UserEmailVerificationCodeDetails;
import com.example.book_your_show.requestDTO.UserEmailVerificationCodeRequest;

import java.util.Optional;

public interface UserEmailVerificationCodeDetailsService {
    public Optional<UserEmailVerificationCodeDetails> findUserEmailVerificationCodeByEmailId(String email);
    public void addUserEmailVerificationCode(UserEmailVerificationCodeRequest userEmailVerificationCodeRequest)throws Exception;
}
