package com.example.book_your_show.service.impl;

import com.example.book_your_show.entities.User;
import com.example.book_your_show.repository.UserRepository;
import com.example.book_your_show.requestDTO.UserRequest;
import com.example.book_your_show.transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
    @Autowired
    private UserRepository userRepository;

    //Method 1: add Details
    public String addDetails(UserRequest userRequest){
        User user= UserTransformer.userRequestToUser(userRequest);
        User savedUser=userRepository.save(user);
        return "user registered successfully";
    }
}
