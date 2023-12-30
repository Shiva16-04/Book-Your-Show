package com.example.book_your_show.responseDTO;

import java.time.LocalDate;
import java.util.List;

public class FilmMakerResponse {
    String name;
    List<ProfessionResponse> professionResponseList;
    LocalDate dateOfBirth;
    String birthPlace;
}
