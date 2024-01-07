package com.example.book_your_show.service.impl;

import com.example.book_your_show.entities.UserEmailVerificationCodeDetails;
import com.example.book_your_show.repository.UserEmailVerificationCodeDetailsRepository;
import com.example.book_your_show.requestDTO.UserEmailVerificationCodeRequest;
import com.example.book_your_show.service.UserEmailVerificationCodeDetailsService;
import com.example.book_your_show.transformers.UserEmailVerificationCodeDetailsTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserEmailVerificationCodeDetailsServiceImpl implements UserEmailVerificationCodeDetailsService {
    @Autowired
    private UserEmailVerificationCodeDetailsRepository userEmailVerificationCodeRepository;

    public Optional<UserEmailVerificationCodeDetails> findUserEmailVerificationCodeByEmailId(String email){
        Optional<UserEmailVerificationCodeDetails>userEmailVerificationCode=userEmailVerificationCodeRepository.findByEmailId(email);
        return userEmailVerificationCode;
    }
    public void addUserEmailVerificationCode(UserEmailVerificationCodeRequest  userEmailVerificationCodeRequest)throws Exception{
        UserEmailVerificationCodeDetails userEmailVerificationCode= UserEmailVerificationCodeDetailsTransformer.UserEmailVerificationCodeRequestToUserEmailVerificationCode(userEmailVerificationCodeRequest);
        userEmailVerificationCodeRepository.save(userEmailVerificationCode);
    }
}
