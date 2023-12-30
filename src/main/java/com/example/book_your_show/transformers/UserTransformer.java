package com.example.book_your_show.transformers;

import com.example.book_your_show.entities.User;
import com.example.book_your_show.requestDTO.UserRequest;

public class UserTransformer {
    public static User userRequestToUser(UserRequest userRequest){
        return User.builder()
                .name(userRequest.getName())
                .age(userRequest.getAge())
                .emailId(userRequest.getEmailId())
                .contactNumber(userRequest.getContactNumber())
                .build();
    }
}
