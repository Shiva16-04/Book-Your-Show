package com.example.book_your_show.controller;

import com.example.book_your_show.enums.FormatEnum;
import com.example.book_your_show.enums.GenreEnum;
import com.example.book_your_show.enums.LanguageEnum;
import com.example.book_your_show.enums.ProfessionEnum;
import com.example.book_your_show.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("general")
public class GeneralController {
   @Autowired
   private GeneralService generalService;

    @PostMapping("/add-language")
    public ResponseEntity addLanguage(@RequestParam List<LanguageEnum> languageEnumList){
        try {
            return new ResponseEntity(generalService.addLanguage(languageEnumList), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add-genre")
    public ResponseEntity addGenre(@RequestParam List<GenreEnum> genreEnumList){
        try {
            return new ResponseEntity(generalService.addGenre(genreEnumList), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add-format")
    public ResponseEntity addFormat(@RequestParam List<FormatEnum> formatEnumList){
        try {
            return new ResponseEntity(generalService.addFormat(formatEnumList), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add-profession")
    public ResponseEntity addProfession(@RequestParam List<ProfessionEnum> professionEnumList){
        try {
            return new ResponseEntity(generalService.addProfession(professionEnumList), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
