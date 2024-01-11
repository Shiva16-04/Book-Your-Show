package com.example.book_your_show.service;

import com.example.book_your_show.requestDTO.ScreenSeatRequest;

import java.util.List;

public interface ScreenSeatService {
    public String addScreenSeats(String theatreCode, String screenNumber, List<ScreenSeatRequest>screenSeatRequestList, List<String>pdSeatList)throws Exception;
}
