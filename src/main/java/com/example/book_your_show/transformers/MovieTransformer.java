package com.example.book_your_show.transformers;

import com.example.book_your_show.entities.Movie;
import com.example.book_your_show.enums.FilmCertificationCategory;
import com.example.book_your_show.enums.FormatEnum;
import com.example.book_your_show.enums.GenreEnum;
import com.example.book_your_show.enums.LanguageEnum;
import com.example.book_your_show.requestDTO.MovieRequest;

import java.util.List;

public class MovieTransformer {
    public static Movie movieRequestToMovie(MovieRequest movieRequest,
                                            FilmCertificationCategory filmCertificationCategory){
        return Movie.builder()
                .name(movieRequest.getName())
                .releaseDate(movieRequest.getReleaseDate())
                .screenTime(movieRequest.getScreenTime())
                .filmCertificationCategory(filmCertificationCategory)
                .build();
    }
}
