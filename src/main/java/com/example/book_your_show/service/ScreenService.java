package com.example.book_your_show.service;

import com.example.book_your_show.entities.Screen;
import com.example.book_your_show.requestDTO.ScreenRequest;

import java.util.List;

public interface ScreenService {
    public String addScreens(String theatreCode, List<ScreenRequest>screenRequestList)throws Exception;
    public String deleteScreen(String theatreCode, String screenNumber)throws Exception;
    public Screen getScreenByTheatreCodeAndScreenNumber(String theatreCode, String screenNumber)throws Exception;
}
