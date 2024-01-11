package com.example.book_your_show.service.impl;

import com.example.book_your_show.entities.Movie;
import com.example.book_your_show.entities.Screen;
import com.example.book_your_show.entities.Show;
import com.example.book_your_show.entities.Theatre;
import com.example.book_your_show.exceptions.InvalidShowCodeException;
import com.example.book_your_show.exceptions.ShowCannotBeAddedException;
import com.example.book_your_show.generators.ShowCodeGenerator;
import com.example.book_your_show.repository.ShowRepository;
import com.example.book_your_show.requestDTO.ShowRequest;
import com.example.book_your_show.service.*;
import com.example.book_your_show.transformers.ShowTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ShowServiceImpl implements ShowService {
    @Autowired
    private ShowSeatService showSeatService;
    @Autowired
    private ScreenService screenService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private TheatreService theatreService;
    @Autowired
    private ShowRepository showRepository;
    @Autowired
    private ShowCodeGenerator showCodeGenerator;
    @Transactional(rollbackFor = Exception.class)
    public String addShow(ShowRequest showRequest) throws Exception{

        String movieCode=showRequest.getMovieCode();
        String theatreCode=showRequest.getTheatreCode();
        String screenNumber=showRequest.getScreenNumber();

        Movie movie=movieService.getMovieByMovieCode(movieCode);
        Theatre theatre=theatreService.getTheatreByTheatreCode(theatreCode);
        Screen screen=screenService.getScreenByTheatreCodeAndScreenNumber(theatreCode,screenNumber);

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

        show.setScreen(screen); //setting the foreign key
        show.setMovie(movie); //setting the foreign key

        screen.getShowList().add(show); //setting bidirectional mapping
        movie.getShowList().add(show); //setting bidirectional mapping
        Show showRes=showRepository.save(show);

        showSeatService.addShowSeats(show.getCode(), screenNumber, theatreCode, showRequest.getShowSeatRequest());

        return showRes.getCode();
    }
    public Show getShowByShowCode(String showCode)throws Exception{
        Optional<Show>optionalShow=showRepository.findByCode(showCode);
        if(!optionalShow.isPresent()){
            throw new InvalidShowCodeException("Show code is invalid!!!Try passing the valid Show Code");
        }
        return optionalShow.get();
    }

}
