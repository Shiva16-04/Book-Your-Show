package com.example.book_your_show.service;

import com.example.book_your_show.entities.Show;
import com.example.book_your_show.requestDTO.ShowRequest;

public interface ShowService {
    public String addShow(ShowRequest showRequest)throws Exception;
    public Show getShowByShowCode(String showCode)throws Exception;
}
