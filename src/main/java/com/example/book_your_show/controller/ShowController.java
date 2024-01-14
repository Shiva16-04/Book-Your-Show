package com.example.book_your_show.controller;

import com.example.book_your_show.enums.City;
import com.example.book_your_show.enums.FormatEnum;
import com.example.book_your_show.enums.LanguageEnum;
import com.example.book_your_show.requestDTO.ShowRequest;
import com.example.book_your_show.service.ShowService;
import com.example.book_your_show.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController
@RequestMapping("show")
public class ShowController {
    @Autowired
    private ShowService showService;
    @Autowired
    private TheatreService theatreService;
    @PostMapping("/add-show")
    public ResponseEntity addShow(@RequestBody ShowRequest showRequest){
        try {
            return new ResponseEntity<>(showService.addShow(showRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/get-filtered-show-list")
    public ResponseEntity getFilteredTheatreShowResponseList(@RequestParam City city,
                                                             @RequestParam (required = false)String movieCode,
                                                             @RequestParam (required = false)LanguageEnum languageEnum,
                                                             @RequestParam (required = false)FormatEnum formatEnum,
                                                             @RequestParam (required = false)LocalDate showDate,
                                                             @RequestParam (required = false)LocalTime startTimeRange,
                                                             @RequestParam (required = false)LocalTime endTimeRange,
                                                             @RequestParam (required = false)String theatreCode){
        return new ResponseEntity<>(showService.getFilteredTheatreShowResponseList(city, movieCode, languageEnum, formatEnum, showDate, startTimeRange, endTimeRange, theatreCode),HttpStatus.OK);
    }
}
