package com.example.book_your_show.service;

import com.example.book_your_show.entities.Screen;
import com.example.book_your_show.entities.Theatre;
import com.example.book_your_show.requestDTO.TheatreRequest;

import java.util.List;

public interface ScreenService {
    public List<Screen> createScreens(Theatre theatre, TheatreRequest theatreRequest);
}
