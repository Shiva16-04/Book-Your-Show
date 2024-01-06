package com.example.book_your_show.repository;

import com.example.book_your_show.entities.Language;
import com.example.book_your_show.enums.LanguageEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
    Optional<Language>findByName(LanguageEnum languageEnum);
    List<Language> findByNameIn(List<LanguageEnum> languageEnumList);
}
