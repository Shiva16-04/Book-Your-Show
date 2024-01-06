package com.example.book_your_show.controller;

import com.example.book_your_show.enums.FilmCertificationCategory;
import com.example.book_your_show.enums.FormatEnum;
import com.example.book_your_show.enums.GenreEnum;
import com.example.book_your_show.enums.LanguageEnum;
import com.example.book_your_show.requestDTO.MovieRequest;
import com.example.book_your_show.service.MovieService;
import com.example.book_your_show.service.impl.MovieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping("/add-movie")
    public ResponseEntity addMovie(@RequestBody MovieRequest movieRequest,
                                   @RequestParam FilmCertificationCategory filmCertificationCategory,
                                   @RequestParam List<FormatEnum> formatList,
                                   @RequestParam List<LanguageEnum> languagesReleasedIn,
                                   @RequestParam List<GenreEnum> movieGenreEnum){
        try {
            return new ResponseEntity<>(movieService.addMovie(movieRequest, filmCertificationCategory, formatList, languagesReleasedIn, movieGenreEnum), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
