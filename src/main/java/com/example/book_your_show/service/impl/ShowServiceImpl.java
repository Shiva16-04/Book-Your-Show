package com.example.book_your_show.service.impl;

import com.example.book_your_show.entities.*;
import com.example.book_your_show.exceptions.InvalidMovieCodeException;
import com.example.book_your_show.exceptions.InvalidTheatreCodeException;
import com.example.book_your_show.exceptions.ScreenNotFoundException;
import com.example.book_your_show.exceptions.ShowCannotBeAddedException;
import com.example.book_your_show.generators.ShowCodeGenerator;
import com.example.book_your_show.repository.MovieRepository;
import com.example.book_your_show.repository.ScreenRepository;
import com.example.book_your_show.repository.ShowRepository;
import com.example.book_your_show.repository.TheatreRepository;
import com.example.book_your_show.requestDTO.ShowRequest;
import com.example.book_your_show.service.ShowSeatService;
import com.example.book_your_show.service.ShowService;
import com.example.book_your_show.transformers.ShowTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ShowServiceImpl implements ShowService {
    @Autowired
    private ShowSeatService showSeatService;
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TheatreRepository theatreRepository;
    @Autowired
    private ScreenRepository screenRepository;
    @Autowired
    private ShowCodeGenerator showCodeGenerator;

    public String addShow(ShowRequest showRequest) throws Exception{

        String movieCode=showRequest.getMovieCode();
        String theatreCode=showRequest.getTheatreCode();
        String screenNumber=showRequest.getScreenNumber();


        Optional<Movie> optionalMovie=movieRepository.findByCode(movieCode);
        if(optionalMovie.isEmpty()){
            throw new InvalidMovieCodeException("Movie Code is invalid. Try requesting for show again with correct details");
        }
        Optional<Theatre> optionalTheatre=theatreRepository.findByCode(theatreCode);
        if(optionalTheatre.isEmpty()){
            throw new InvalidTheatreCodeException("Theatre Code is Invalid. Try requesting for show again with correct details");
        }
        Optional<Screen> optionalScreen=screenRepository.findByTheatreCodeAndScreenNumber(theatreCode, screenNumber);
        if(optionalScreen.isEmpty()){
            throw new ScreenNotFoundException("Screen not found with the particular screen number and theatre code combination. Try again with correct information");
        }
        Movie movie=optionalMovie.get();
        Theatre theatre=optionalTheatre.get();
        Screen screen=optionalScreen.get();


        //arranging (or) truncating time up to minutes by eliminating seconds
        LocalTime screenTime=movie.getScreenTime().truncatedTo(ChronoUnit.MINUTES);
        LocalDate movieReleaseDate=movie.getReleaseDate();

        LocalDateTime startTime=showRequest.getStartTime().truncatedTo(ChronoUnit.MINUTES);

        //checking whether the show creation request is before release date or after release date
        if(startTime.toLocalDate().isBefore(movieReleaseDate)){
            throw new ShowCannotBeAddedException("Show cannot be added because show timings are before release date");
        }
        //Calculating end time of show based on start time and movie screen time
        LocalDateTime endTime=startTime.plusHours(screenTime.getHour()).plusMinutes(screenTime.getMinute());


        //validating if there is any overlap of show by adding this show in that particular screen
        List<Show>showList=showRepository.findShowsByScreenNumberAndTimeRangeAndTheatreCode(screenNumber, startTime, endTime, startTime, endTime, theatreCode);
        if(showList.size()!=0){
            throw new ShowCannotBeAddedException("Show cannot be added in the particular time as other shows are overlapping");
        }

        Show show= ShowTransformer.ShowRequestToShow(showRequest, endTime);

        String code= showCodeGenerator.generate("SHW");

        //setting the attributes
        show.setCode(code);

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
