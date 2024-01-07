package com.example.book_your_show.service.impl;

import com.example.book_your_show.entities.*;
import com.example.book_your_show.enums.FilmCertificationCategory;
import com.example.book_your_show.enums.FormatEnum;
import com.example.book_your_show.enums.GenreEnum;
import com.example.book_your_show.enums.LanguageEnum;
import com.example.book_your_show.exceptions.InvalidMovieCodeException;
import com.example.book_your_show.exceptions.MovieAlreadyPresentException;
import com.example.book_your_show.generators.MovieCodeGenerator;
import com.example.book_your_show.repository.MovieRepository;
import com.example.book_your_show.requestDTO.MovieRequest;
import com.example.book_your_show.service.FilmMakerService;
import com.example.book_your_show.service.GeneralService;
import com.example.book_your_show.service.MovieService;
import com.example.book_your_show.transformers.MovieTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private GeneralService generalService;
    @Autowired
    private FilmMakerService filmMakerService;
    @Autowired
    private MovieCodeGenerator movieCodeGenerator;

    //Method 1: adding movie details
    public String addMovie(MovieRequest movieRequest,
                        FilmCertificationCategory filmCertificationCategory,
                        List<FormatEnum> formatEnumList,
                        List<LanguageEnum> languagesReleasedInEnumList,
                        List<GenreEnum> movieGenreEnumList)throws Exception{
        Optional<Movie>optionalMovie=movieRepository.findByName(movieRequest.getName());
        if(optionalMovie.isPresent()){
            throw new MovieAlreadyPresentException("Movie "+movieRequest.getName()+" is already present in the database");
        }
        Movie movie= MovieTransformer.movieRequestToMovie(movieRequest, filmCertificationCategory);

        String code= movieCodeGenerator.generate("MVE");

        //setting the attribute
        movie.setCode(code);


        List<Format>formatList=generalService.getFormatList(formatEnumList);
        List<Language>languagesReleasedIn=generalService.getLanguageList(languagesReleasedInEnumList);
        List<Genre>genreList=generalService.getGenreList(movieGenreEnumList);
        List<FilmMaker>filmMakerList=filmMakerService.getFilmMaker(movieRequest.getFilmMakerIdList());

        //setting foreign keys
        movie.setFormatList(formatList);
        movie.setLanguagesReleasedIn(languagesReleasedIn);
        movie.setGenreList(genreList);
        movie.setFilmMakersList(filmMakerList);

        //bi directionally mapping the foreign keys
        for(Format format:formatList)format.getMovieList().add(movie);
        for(Language language: languagesReleasedIn)language.getMovieList().add(movie);
        for(Genre genre: genreList)genre.getMovieList().add(movie);
        for(FilmMaker filmMaker: filmMakerList)filmMaker.getMovieList().add(movie);

        Movie savedMovie=movieRepository.save(movie);
        return "Movie has been registered successfully with Movie code: "+savedMovie;
    }
    public Movie getMovieByMovieCode(String movieCode)throws Exception{
        Optional<Movie> optionalMovie=movieRepository.findByCode(movieCode);
        if(optionalMovie.isEmpty()){
            throw new InvalidMovieCodeException("Movie Code is invalid. Try requesting for show again with correct details");
        }
        return optionalMovie.get();
    }
}
