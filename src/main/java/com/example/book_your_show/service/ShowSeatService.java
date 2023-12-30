package com.example.book_your_show.service;

import com.example.book_your_show.entities.Screen;
import com.example.book_your_show.entities.Show;
import com.example.book_your_show.entities.ShowSeat;
import com.example.book_your_show.requestDTO.ShowRequest;

import java.util.List;

public interface ShowSeatService {
    public List<ShowSeat> createShowSeats(Show show, Screen screen, ShowRequest showRequest);
}
