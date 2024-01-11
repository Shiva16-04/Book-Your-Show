package com.example.book_your_show.service.impl;

import com.example.book_your_show.entities.FilmMaker;
import com.example.book_your_show.entities.Profession;
import com.example.book_your_show.enums.ProfessionEnum;
import com.example.book_your_show.exceptions.FilmMakerAlreadyPresentException;
import com.example.book_your_show.generators.FilmMakerCodeGenerator;
import com.example.book_your_show.repository.FilmMakerRepository;
import com.example.book_your_show.requestDTO.FilmMakerRequest;
import com.example.book_your_show.service.FilmMakerService;
import com.example.book_your_show.service.GeneralService;
import com.example.book_your_show.transformers.FilmMakerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmMakerServiceImpl implements FilmMakerService {
    @Autowired
    private FilmMakerRepository filmMakerRepository;
    @Autowired
    private GeneralService generalService;
    @Autowired
    private FilmMakerCodeGenerator filmMakerCodeGenerator;

    public String addFilmMaker(FilmMakerRequest filmMakerRequest, List<ProfessionEnum> professionEnumList)throws Exception{
        Optional<FilmMaker>optionalFilmMaker=filmMakerRepository.findByEmailId(filmMakerRequest.getEmailId());
        if(optionalFilmMaker.isPresent()){
            throw new FilmMakerAlreadyPresentException("Film Maker is already present with this particular email Id "+filmMakerRequest.getEmailId());
        }
        FilmMaker filmMaker= FilmMakerTransformer.filmMakerRequestToFilmMaker(filmMakerRequest);

        List<Profession>professionList=generalService.getProfessionList(professionEnumList);
        String code= filmMakerCodeGenerator.generate("FMK");

        //setting the attributes and foreign keys
        filmMaker.setCode(code);
        filmMaker.setProfessionList(professionList);

        //bidirectionally mapping
        for(Profession profession: professionList){
            profession.getFilmMakerList().add(filmMaker);
        }
        FilmMaker savedFilmMaker=filmMakerRepository.save(filmMaker);
        return savedFilmMaker.getCode();
    }
    public List<FilmMaker> getFilmMaker(List<String>filmMakerCodeList){
        return filmMakerRepository.findByCodeIn(filmMakerCodeList);
    }
}
