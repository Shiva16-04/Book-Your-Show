package com.example.book_your_show.service.impl;

import com.example.book_your_show.entities.Movie;
import com.example.book_your_show.repository.MovieRepository;
import com.example.book_your_show.requestDTO.MovieRequest;
import com.example.book_your_show.service.MovieService;
import com.example.book_your_show.transformers.MovieTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    //Method 1: adding movie details
    public int addMovie(MovieRequest movieRequest){
        Movie movie= MovieTransformer.movieRequestToMovie(movieRequest);
        Movie movieRes=movieRepository.save(movie);
        return movieRes.getId();
    }
}
