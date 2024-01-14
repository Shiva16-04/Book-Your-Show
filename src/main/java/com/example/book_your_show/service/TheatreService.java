package com.example.book_your_show.service;

import com.example.book_your_show.entities.Theatre;
import com.example.book_your_show.enums.City;
import com.example.book_your_show.enums.FormatEnum;
import com.example.book_your_show.enums.LanguageEnum;
import com.example.book_your_show.requestDTO.TheatreRequest;
import com.example.book_your_show.responseDTO.TheatreResponseShow;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TheatreService {
    public String addTheatre(TheatreRequest theatreRequest)throws Exception;
    public Theatre getTheatreByTheatreCode(String theatreCode)throws Exception;
}
