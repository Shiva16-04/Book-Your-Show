package com.example.book_your_show.transformers;

import com.example.book_your_show.entities.UserEmailVerificationCodeDetails;
import com.example.book_your_show.requestDTO.UserEmailVerificationCodeRequest;

public class UserEmailVerificationCodeDetailsTransformer {
    public static UserEmailVerificationCodeDetails UserEmailVerificationCodeRequestToUserEmailVerificationCode(UserEmailVerificationCodeRequest userEmailVerificationCodeRequest){
        return UserEmailVerificationCodeDetails.builder()
                .emailId(userEmailVerificationCodeRequest.getEmail())
                .verificationCode(userEmailVerificationCodeRequest.getVerificationCode())
                .build();
    }
}
