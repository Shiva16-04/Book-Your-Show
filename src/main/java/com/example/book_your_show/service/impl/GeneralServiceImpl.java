package com.example.book_your_show.service.impl;

import com.example.book_your_show.entities.Format;
import com.example.book_your_show.entities.Genre;
import com.example.book_your_show.entities.Language;
import com.example.book_your_show.entities.Profession;
import com.example.book_your_show.enums.FormatEnum;
import com.example.book_your_show.enums.GenreEnum;
import com.example.book_your_show.enums.LanguageEnum;
import com.example.book_your_show.enums.ProfessionEnum;
import com.example.book_your_show.exceptions.FormatAlreadyPresentException;
import com.example.book_your_show.exceptions.GenreAlreadyPresentException;
import com.example.book_your_show.exceptions.LanguageAlreadyPresentException;
import com.example.book_your_show.exceptions.ProfessionAlreadyPresentException;
import com.example.book_your_show.repository.FormatRepository;
import com.example.book_your_show.repository.GenreRepository;
import com.example.book_your_show.repository.LanguageRepository;
import com.example.book_your_show.repository.ProfessionRepository;
import com.example.book_your_show.service.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GeneralServiceImpl implements GeneralService {
    @Autowired
    private FormatRepository formatRepository;
    @Autowired
    private LanguageRepository languageRepository;
    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private ProfessionRepository professionRepository;


    public Object addLanguage(List<LanguageEnum> languageEnumList)throws Exception{
        List<String>alreadyPresentLanguageList=new ArrayList<>();
        List<String>alreadyNotPresentLanguageList=new ArrayList<>();
        for(LanguageEnum languageEnum: languageEnumList){
            Optional<Language>optionalLanguage=languageRepository.findByName(languageEnum);
            if(!optionalLanguage.isPresent()){
                Language language=new Language();
                language.setName(languageEnum);
                languageRepository.save(language);
                alreadyNotPresentLanguageList.add(languageEnum.getDisplayName());
            }else{
                alreadyPresentLanguageList.add(languageEnum.getDisplayName());
            }
        }
        if(alreadyPresentLanguageList.size()!=0){
            throw new LanguageAlreadyPresentException("Cannot add. Language(s) "+alreadyPresentLanguageList+" is/are present in the database");
        }else{
            return "language(s) "+alreadyNotPresentLanguageList+" added to the database successfully";
        }
    }
    public List<Language> getLanguageList(List<LanguageEnum> languageEnumList) {
        return languageRepository.findByNameIn(languageEnumList);
    }

    public Object addGenre(List<GenreEnum> genreEnumList)throws Exception{
        List<String> alreadyPresentGenreList = new ArrayList<>();
        List<String> alreadyNotPresentGenreList = new ArrayList<>();

        for (GenreEnum genreEnum : genreEnumList) {
            Optional<Genre> optionalGenre = genreRepository.findByName(genreEnum);

            if (optionalGenre.isEmpty()) {
                Genre genre = new Genre();
                genre.setName(genreEnum);
                genreRepository.save(genre);
                alreadyNotPresentGenreList.add(genreEnum.getDisplayName());
            } else {
                alreadyPresentGenreList.add(genreEnum.getDisplayName());
            }
        }

        if (!alreadyPresentGenreList.isEmpty()) {
            throw new GenreAlreadyPresentException("Cannot add. Genre(s) " + alreadyPresentGenreList + " is/are present in the database");
        } else {
            return "Genre(s) " + alreadyNotPresentGenreList + " added to the database successfully";
        }
    }
    public List<Genre> getGenreList(List<GenreEnum> genreEnumList){
        List<Genre>genreList=genreRepository.findByNameIn(genreEnumList);
        return genreList;
    }

    public Object addFormat(List<FormatEnum> formatEnumList)throws Exception{
        List<String> alreadyPresentFormatList = new ArrayList<>();
        List<String> alreadyNotPresentFormatList = new ArrayList<>();

        for (FormatEnum formatEnum : formatEnumList) {
            Optional<Format> optionalFormat = formatRepository.findByName(formatEnum);

            if (optionalFormat.isEmpty()) {
                Format format = new Format();
                format.setName(formatEnum);
                formatRepository.save(format);
                alreadyNotPresentFormatList.add(formatEnum.getDisplayName());
            } else {
                alreadyPresentFormatList.add(formatEnum.getDisplayName());
            }
        }

        if (!alreadyPresentFormatList.isEmpty()) {
            throw new FormatAlreadyPresentException("Cannot add. Format(s) " + alreadyPresentFormatList + " is/are present in the database");
        } else {
            return "Format(s) " + alreadyNotPresentFormatList + " added to the database successfully";
        }
    }
    public List<Format> getFormatList(List<FormatEnum> formatEnumList) {
        return formatRepository.findByNameIn(formatEnumList);
    }


    public Object addProfession(List<ProfessionEnum> professionEnumList)throws Exception{
        List<String> alreadyPresentProfessionList = new ArrayList<>();
        List<String> alreadyNotPresentProfessionList = new ArrayList<>();

        for (ProfessionEnum professionEnum : professionEnumList) {
            Optional<Profession> optionalProfession = professionRepository.findByName(professionEnum);

            if (optionalProfession.isEmpty()) {
                Profession profession = new Profession();
                profession.setName(professionEnum);
                professionRepository.save(profession);
                alreadyNotPresentProfessionList.add(professionEnum.getDisplayName());
            } else {
                alreadyPresentProfessionList.add(professionEnum.getDisplayName());
            }
        }

        if (!alreadyPresentProfessionList.isEmpty()) {
            throw new ProfessionAlreadyPresentException("Cannot add. Profession(s) " + alreadyPresentProfessionList + " is/are present in the database");
        } else {
            return "Profession(s) " + alreadyNotPresentProfessionList + " added to the database successfully";
        }
    }
    public List<Profession> getProfessionList(List<ProfessionEnum> professionEnumList) {
        return professionRepository.findByNameIn(professionEnumList);
    }
}
