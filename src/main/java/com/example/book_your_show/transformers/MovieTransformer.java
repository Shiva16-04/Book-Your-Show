package com.example.book_your_show.transformers;

import com.example.book_your_show.entities.Movie;
import com.example.book_your_show.requestDTO.MovieRequest;

public class MovieTransformer {
    public static Movie movieRequestToMovie(MovieRequest movieRequest){
        return Movie.builder()
                .name(movieRequest.getName())
                .releaseDate(movieRequest.getReleaseDate())
                .screenTime(movieRequest.getScreenTime())
                .format(movieRequest.getFormat())
                .filmCertificationCategory(movieRequest.getFilmCertificationCategory())
//                .languagesReleasedIn(movieRequest.getLanguagesReleasedIn())
//                .movieGenreEnum(movieRequest.getMovieGenreEnum())
                .build();
    }
}
