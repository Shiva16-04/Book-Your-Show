package com.example.book_your_show.service;

import com.example.book_your_show.entities.Screen;
import com.example.book_your_show.entities.ScreenSeat;
import com.example.book_your_show.requestDTO.ScreenSeatRequest;

import java.util.List;

public interface ScreenSeatService {
    public List<ScreenSeat> createTheatreSeats(Screen screen, List<ScreenSeatRequest>screenSeatRequestList, List<String>pdSeatList);
}
