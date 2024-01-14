package com.example.book_your_show.service;

import com.example.book_your_show.entities.Show;
import com.example.book_your_show.enums.City;
import com.example.book_your_show.enums.FormatEnum;
import com.example.book_your_show.enums.LanguageEnum;
import com.example.book_your_show.requestDTO.ShowRequest;
import com.example.book_your_show.responseDTO.TheatreResponseShow;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ShowService {
    public String addShow(ShowRequest showRequest)throws Exception;
    public List<TheatreResponseShow> getFilteredTheatreShowResponseList(City city, String movieCode, LanguageEnum languageEnum, FormatEnum formatEnum, LocalDate showDate, LocalTime startTimeRange, LocalTime endTimeRange , String theatreCode);
    public Show getShowByShowCode(String showCode)throws Exception;
}
