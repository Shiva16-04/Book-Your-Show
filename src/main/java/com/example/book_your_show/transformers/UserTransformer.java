package com.example.book_your_show.transformers;

import com.example.book_your_show.entities.User;
import com.example.book_your_show.requestDTO.UserRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

public class UserTransformer {
    public static User userRequestToUser(UserRequest userRequest){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return User.builder()
                .name(userRequest.getName())
                .age(userRequest.getAge())
                .emailId(userRequest.getEmailId())
                .contactNumber(userRequest.getContactNumber())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .ticketList(new ArrayList<>())
                .build();
    }
}
