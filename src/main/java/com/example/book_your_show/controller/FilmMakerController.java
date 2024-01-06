package com.example.book_your_show.controller;

import com.example.book_your_show.entities.FilmMaker;
import com.example.book_your_show.entities.Profession;
import com.example.book_your_show.enums.ProfessionEnum;
import com.example.book_your_show.requestDTO.FilmMakerRequest;
import com.example.book_your_show.service.FilmMakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/film-makers")
public class FilmMakerController {
    @Autowired
    private FilmMakerService filmMakerService;

    @PostMapping("/add-film-maker")
    public ResponseEntity addFilmMaker(@RequestBody FilmMakerRequest filmMakerRequest,
                                       @RequestParam List<ProfessionEnum>professionEnumList){
        try {
            return new ResponseEntity(filmMakerService.addFilmMaker(filmMakerRequest, professionEnumList), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
