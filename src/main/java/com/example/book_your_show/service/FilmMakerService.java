package com.example.book_your_show.service;

import com.example.book_your_show.entities.FilmMaker;
import com.example.book_your_show.enums.ProfessionEnum;
import com.example.book_your_show.requestDTO.FilmMakerRequest;

import java.util.List;

public interface FilmMakerService {
    public String addFilmMaker(FilmMakerRequest filmMakerRequest, List<ProfessionEnum> professionEnumList)throws Exception;
    public List<FilmMaker> getFilmMaker(List<String>filmMakerCodeList);
}
