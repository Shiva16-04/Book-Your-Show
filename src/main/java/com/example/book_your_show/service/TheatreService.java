package com.example.book_your_show.service;

import com.example.book_your_show.entities.Theatre;
import com.example.book_your_show.requestDTO.TheatreRequest;

public interface TheatreService {
    public String addTheatre(TheatreRequest theatreRequest)throws Exception;
    public Theatre getTheatreByTheatreCode(String theatreCode)throws Exception;
}
