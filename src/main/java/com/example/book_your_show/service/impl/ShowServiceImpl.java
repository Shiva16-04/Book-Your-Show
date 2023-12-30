package com.example.book_your_show.service.impl;

import com.example.book_your_show.entities.*;
import com.example.book_your_show.repository.MovieRepository;
import com.example.book_your_show.repository.ShowRepository;
import com.example.book_your_show.repository.TheatreRepository;
import com.example.book_your_show.requestDTO.ShowRequest;
import com.example.book_your_show.service.ShowSeatService;
import com.example.book_your_show.service.ShowService;
import com.example.book_your_show.transformers.ShowTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowServiceImpl implements ShowService {
    @Autowired
    private ShowSeatService showSeatService;
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TheatreRepository theatreRepository;

    public String addShow(ShowRequest showRequest) {
        Movie movie=movieRepository.findByCode(showRequest.getMovieCode()).get();
        Theatre theatre=theatreRepository.findByCode(showRequest.getTheatreCode()).get();
        Screen screen=null;
        List<Screen>screenList=theatre.getScreens();
        for(Screen screenValue: screenList){
            if(screenValue.getScreenNumber().equals(showRequest.getScreenNumber()))screen=screenValue;
        }
        Show show= ShowTransformer.ShowRequestToShow(showRequest);

        show.getScreenList().add(screen); //setting the foreign key
        show.setMovie(movie); //setting the foreign key

        screen.getShowList().add(show); //setting bidirectional mapping
        movie.getShowList().add(show); //setting bidirectional mapping

        List<ShowSeat>showSeatList=showSeatService.createShowSeats(show, screen, showRequest);
        show.setShowSeatList(showSeatList);
        Show showRes=showRepository.save(show);
        return showRes.getCode();
    }

}
