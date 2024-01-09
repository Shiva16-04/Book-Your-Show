package com.example.book_your_show.service.impl;

import com.example.book_your_show.service.AuthenticationDetailsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationDetailsServiceImpl implements AuthenticationDetailsService {
    public String getAuthenticationDetails(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails= (UserDetails)authentication.getPrincipal();
        String userEmailId= userDetails.getUsername();
        return userEmailId;
    }
}
