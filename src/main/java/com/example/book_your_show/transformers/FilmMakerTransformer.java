package com.example.book_your_show.transformers;

import com.example.book_your_show.entities.FilmMaker;
import com.example.book_your_show.requestDTO.FilmMakerRequest;
import com.example.book_your_show.responseDTO.FilmMakerResponse;
import org.springframework.web.bind.annotation.RequestBody;

public class FilmMakerTransformer {
    public static FilmMaker filmMakerRequestToFilmMaker(@RequestBody FilmMakerRequest filmMakerRequest){
        return FilmMaker.builder()
                .name(filmMakerRequest.getName())
                .dateOfBirth(filmMakerRequest.getDateOfBirth())
                .birthPlace(filmMakerRequest.getBirthPlace())
                .emailId(filmMakerRequest.getEmailId())
                .build();
    }
    public static FilmMakerResponse filmMakerToFilmMakerResponse(FilmMaker filmMaker){
        return FilmMakerResponse.builder()
                .code(filmMaker.getCode())
                .name(filmMaker.getName())
                .dateOfBirth(filmMaker.getDateOfBirth())
                .birthPlace(filmMaker.getBirthPlace())
                .build();
    }
}
