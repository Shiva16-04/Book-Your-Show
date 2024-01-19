package com.example.book_your_show.service;

import com.example.book_your_show.entities.ShowSeat;
import com.example.book_your_show.requestDTO.ShowSeatRequest;

import java.util.List;

public interface ShowSeatService {
    public String addShowSeats(String showCode, String screenNumber, String theatreCode,  ShowSeatRequest showSeatRequest)throws Exception;
    public String deleteShowSeats(String showCode, List<String>showSeatNumnerList) throws Exception;
    public String deleteShowSeats(List<ShowSeat>showSeatList)throws Exception;
    public List<ShowSeat> findShowSeatsByShowCodeAndShowSeatNoList(String showCode, List<String>showSeatNumberList);
}
