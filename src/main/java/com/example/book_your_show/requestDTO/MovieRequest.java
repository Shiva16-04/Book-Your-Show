package com.example.book_your_show.requestDTO;

import com.example.book_your_show.enums.FilmCertificationCategory;
import com.example.book_your_show.enums.Format;
import com.example.book_your_show.enums.GenreEnum;
import com.example.book_your_show.enums.LanguageEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieRequest {
    String name;
    LocalDate releaseDate;
    LocalTime screenTime;
    Format format;
    FilmCertificationCategory filmCertificationCategory;
    List<LanguageEnum> languagesReleasedIn;
    List<GenreEnum> movieGenreEnum;
    List<String>filmMakerIdList;
}
