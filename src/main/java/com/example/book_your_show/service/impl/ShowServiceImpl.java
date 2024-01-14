package com.example.book_your_show.service.impl;

import com.example.book_your_show.entities.*;
import com.example.book_your_show.enums.City;
import com.example.book_your_show.enums.FormatEnum;
import com.example.book_your_show.enums.LanguageEnum;
import com.example.book_your_show.exceptions.InvalidShowCodeException;
import com.example.book_your_show.exceptions.ShowCannotBeAddedException;
import com.example.book_your_show.generators.ShowCodeGenerator;
import com.example.book_your_show.repository.ShowRepository;
import com.example.book_your_show.requestDTO.ShowRequest;
import com.example.book_your_show.responseDTO.ShowResponseTheatre;
import com.example.book_your_show.responseDTO.ShowSeatResponse;
import com.example.book_your_show.responseDTO.TheatreResponseShow;
import com.example.book_your_show.service.*;
import com.example.book_your_show.transformers.ShowSeatTransformer;
import com.example.book_your_show.transformers.ShowTransformer;
import com.example.book_your_show.transformers.TheatreTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
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

        boolean language=movie.hasLanguage(showRequest.getLanguage());
        boolean format=movie.hasFormat(showRequest.getFormatEnum());

        if(language == false || format == false){
            throw new ShowCannotBeAddedException("Show cannot be added as language or format of show of particular movie is not with in the released category");
        }

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

    public List<TheatreResponseShow> getFilteredTheatreShowResponseList(City city, String movieCode, LanguageEnum languageEnum, FormatEnum formatEnum, LocalDate showDate, LocalTime startTimeRange, LocalTime endTimeRange , String theatreCode){
        LocalDateTime startDateTime=showDate==null||startTimeRange==null||endTimeRange==null?null:LocalDateTime.of(showDate, startTimeRange);
        LocalDateTime endDateTime=showDate==null||startTimeRange==null||endTimeRange==null?null:LocalDateTime.of(showDate, endTimeRange);
        String language=languageEnum==null?null:languageEnum.toString();
        String format=formatEnum==null?null:formatEnum.toString();

        List<Show>showList= showRepository.findFilteredTheatreShowResponseList(city.toString(), movieCode, language, format, showDate, startDateTime, endDateTime, theatreCode);
        List<TheatreResponseShow>theatreResponseShowList=new ArrayList<>();
        HashMap<String, List<ShowResponseTheatre>>showResponseTheatreMap=new HashMap<>();
        HashMap<String, TheatreResponseShow>theatreResponseShowMap=new HashMap<>();

        for(Show show: showList){
            Screen screen=show.getScreen();
            Theatre theatre=screen.getTheatre();
            Address address=theatre.getAddress();
            Movie movie=show.getMovie();

            List<ShowSeat>showSeatList=show.getShowSeatList();
            List<ShowSeatResponse>showSeatResponseList=new ArrayList<>();
            for(ShowSeat showSeat:showSeatList){
                showSeatResponseList.add(ShowSeatTransformer.showSeatToShowSeatResponse(showSeat));
            }
            ShowResponseTheatre showResponseTheatre=ShowTransformer.showToShowResponseTheatre(movie.getName(), screen.getScreenNumber(), show, new ArrayList<>());

            List<ShowResponseTheatre>showResponseTheatreList=showResponseTheatreMap.get(theatre.getCode());
            if(showResponseTheatreList==null){
                showResponseTheatreList=new ArrayList<>();
                TheatreResponseShow theatreResponseShow= TheatreTransformer.theatreAndAddressToTheatreResponseShow(theatre.getName(), address, new ArrayList<>());
                theatreResponseShowMap.put(theatre.getCode(), theatreResponseShow);
            }
            showResponseTheatreList.add(showResponseTheatre);
            showResponseTheatreMap.put(theatre.getCode(), showResponseTheatreList);
        }
        if(showResponseTheatreMap.keySet().equals(theatreResponseShowMap.keySet())){
            for(String key: showResponseTheatreMap.keySet()){
                TheatreResponseShow theatreResponseShow=theatreResponseShowMap.get(key);
                theatreResponseShow.setShowResponseTheatreList(showResponseTheatreMap.get(key));
                theatreResponseShowList.add(theatreResponseShow);
            }
        }else{
            System.out.println("Not same keys present");
        }
        return theatreResponseShowList;
    }

    public Show getShowByShowCode(String showCode)throws Exception{
        Optional<Show>optionalShow=showRepository.findByCode(showCode);
        if(!optionalShow.isPresent()){
            throw new InvalidShowCodeException("Show code is invalid!!!Try passing the valid Show Code");
        }
        return optionalShow.get();
    }

}
