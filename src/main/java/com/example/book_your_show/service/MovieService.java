package com.example.book_your_show.service;

import com.example.book_your_show.enums.FilmCertificationCategory;
import com.example.book_your_show.enums.FormatEnum;
import com.example.book_your_show.enums.GenreEnum;
import com.example.book_your_show.enums.LanguageEnum;
import com.example.book_your_show.requestDTO.MovieRequest;

import java.util.List;

public interface MovieService {
    public String addMovie(MovieRequest movieRequest, FilmCertificationCategory filmCertificationCategory,
                        List<FormatEnum> formatList, List<LanguageEnum> languagesReleasedIn,
                        List<GenreEnum> movieGenreEnum)throws Exception;
}
