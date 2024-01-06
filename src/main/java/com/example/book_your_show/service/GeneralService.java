package com.example.book_your_show.service;

import com.example.book_your_show.entities.Format;
import com.example.book_your_show.entities.Genre;
import com.example.book_your_show.entities.Language;
import com.example.book_your_show.entities.Profession;
import com.example.book_your_show.enums.FormatEnum;
import com.example.book_your_show.enums.GenreEnum;
import com.example.book_your_show.enums.LanguageEnum;
import com.example.book_your_show.enums.ProfessionEnum;

import java.util.List;

public interface GeneralService {
    Object addLanguage(List<LanguageEnum> languageEnumList) throws Exception;
    List<Language> getLanguageList(List<LanguageEnum> languageEnumList);

    Object addGenre(List<GenreEnum> genreEnumList) throws Exception;
    List<Genre> getGenreList(List<GenreEnum> genreEnumList);

    Object addFormat(List<FormatEnum> formatEnumList) throws Exception;
    List<Format> getFormatList(List<FormatEnum> formatEnumList);

    Object addProfession(List<ProfessionEnum> professionEnumList) throws Exception;
    List<Profession> getProfessionList(List<ProfessionEnum> professionEnumList);
}
